package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.AppsUpdateBean;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.common.apkparset.AndroidApk;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressSubscriber;
import com.xiaoqianghe.wqplay.common.util.ACache;
import com.xiaoqianghe.wqplay.common.util.AppUtils;
import com.xiaoqianghe.wqplay.common.util.JsonUtils;
import com.xiaoqianghe.wqplay.common.util.PermissionUtil;
import com.xiaoqianghe.wqplay.presenter.contract.MainContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static android.Manifest.permission.READ_PHONE_STATE;

/**
 * Author：Wq
 * Date：2017/12/4 16:50
 * Description：//todo
 */

public class MainPresenter extends BasePresenter<MainContract.IMainModel,MainContract.MainView> {



    @Inject
    public MainPresenter(MainContract.IMainModel mModel, MainContract.MainView mView) {
        super(mModel, mView);
    }

    public void requestPermission(){

//
//        mView.requestPermissonSuccess();
////        mView.requestPermissonFail();


        PermissionUtil.requestPermisson(mContext,READ_PHONE_STATE).doOnNext(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {

                if(!aBoolean){

                    mView.requestPermissonFail();

                }

            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                mView.requestPermissonSuccess();
            }
        });

    }


    public void  getAppUpdateInfo(){

        getIntalledApps()
                .flatMap(new Function<AppsUpdateBean, ObservableSource<List<AppInfo>>>() {
                    @Override
                    public ObservableSource<List<AppInfo>> apply(@NonNull AppsUpdateBean params) throws Exception {

                        return  mModel.getUpdateApps(params).compose(RxHttpResponseCompat.<List<AppInfo>>compatResult());
                    }
                })
                .subscribe(new ProgressSubscriber<List<AppInfo>>(mContext,mView) {
            @Override
            public void onNext(List<AppInfo> appInfos) {

                if(appInfos !=null){

                    ACache.get(mContext).put(Constant.APP_UPDATE_LIST, JsonUtils.toJson(appInfos));
                }

                mView.changeAppNeedUpdateCount(appInfos==null?0:appInfos.size());


            }
        });





    }

    private Observable<AppsUpdateBean>  getIntalledApps(){

        return Observable.create(new ObservableOnSubscribe<AppsUpdateBean>() {
            @Override
            public void subscribe(ObservableEmitter<AppsUpdateBean> e) throws Exception {

                e.onNext(buildParams(AppUtils.getInstalledApps(mContext)));

                e.onComplete();

            }

});
        }
    private AppsUpdateBean buildParams(List<AndroidApk> apks) {

        StringBuilder packageNameBuilder = new StringBuilder();
        StringBuilder versionCodeBuilder = new StringBuilder();

        for(AndroidApk apk :apks){

            if(!apk.isSystem()){

                packageNameBuilder.append(apk.getPackageName()).append(",");
                versionCodeBuilder.append(apk.getAppVersionCode()).append(",");
            }
        }

        AppsUpdateBean param = new AppsUpdateBean();
        param.setPackageName(packageNameBuilder.toString());
        param.setVersionCode(versionCodeBuilder.toString());

        return param;

    }




}
