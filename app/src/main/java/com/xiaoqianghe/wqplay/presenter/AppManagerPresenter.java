package com.xiaoqianghe.wqplay.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.common.apkparset.AndroidApk;
import com.xiaoqianghe.wqplay.common.rx.RxBus;
import com.xiaoqianghe.wqplay.common.rx.RxSchedulers;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressDialogSubscriber;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressSubscriber;
import com.xiaoqianghe.wqplay.common.util.ACache;
import com.xiaoqianghe.wqplay.presenter.contract.AppManagerContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Author：Wq
 * Date：2017/11/30 15:12
 * Description：//todo
 */

public class AppManagerPresenter extends BasePresenter<AppManagerContract.IAppManagerModel,AppManagerContract.AppManagerView> {


    @Inject
    public AppManagerPresenter(AppManagerContract.IAppManagerModel mModel, AppManagerContract.AppManagerView mView) {
        super(mModel, mView);
    }

    public void getDownLoadingApps(){

        mModel.getDownloadRecord().compose(RxSchedulers.<List<DownloadRecord>>io_main())
                .subscribe(new ProgressSubscriber<List<DownloadRecord>>(mContext,mView) {
                    @Override
                    public void onNext(List<DownloadRecord> downloadRecords) {

                        mView.showDownloading(downloadRecordsFilter(downloadRecords));

                    }
                });
    }

    private List<DownloadRecord> downloadRecordsFilter(List<DownloadRecord> downloadRecords) {

        List<DownloadRecord> newList =new ArrayList<>();

        for(DownloadRecord r :downloadRecords){

            if(r.getFlag()!= DownloadFlag.COMPLETED){

                newList.add(r);

            }

        }


        return newList;
    }


    public void getLocalApks(){

        mModel.getLocalApks().compose(RxSchedulers.<List<AndroidApk>>io_main())
                .subscribe(new ProgressSubscriber<List<AndroidApk>>(mContext,mView) {
                    @Override
                    public void onNext(List<AndroidApk> androidApks) {
                        mView.showApps(androidApks);
                    }
                });
    }



    public RxDownload getRxDowanload(){

        return mModel.getRxDownload();
    }

    public void  getUpdateApps(){


        String json =   ACache.get(mContext).getAsString(Constant.APP_UPDATE_LIST);


        if(!TextUtils.isEmpty(json)){

            Gson gson = new Gson();
            List<AppInfo> apps = gson.fromJson(json,new TypeToken<List<AppInfo>>(){}.getType());


            Observable.just(apps)
                    .compose(RxSchedulers.<List<AppInfo>>io_main())

                    .subscribe(new ProgressSubscriber<List<AppInfo>>(mContext,mView) {
                        @Override
                        public void onNext(List<AppInfo> appInfos) {

                            mView.showUpdateApps(appInfos);
                        }
                    });


        }


    }


    public void getInstalledApps(){



        mModel.getInstalledApps().compose(RxSchedulers.<List<AndroidApk>>io_main())
                .subscribe(new ProgressSubscriber<List<AndroidApk>>(mContext,mView) {
                    @Override
                    public void onNext(List<AndroidApk> androidApks) {
                        mView.showApps(androidApks);
                    }
                });
    }





}
