package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressDialogSubscriber;
import com.xiaoqianghe.wqplay.data.AppInfoModel;
import com.xiaoqianghe.wqplay.presenter.contract.AppInfoContract;

import javax.inject.Inject;

/**
 * Author：Wq
 * Date：2017/9/20 9:55
 * Description：//todo
 */

public class AppDetailPresenter extends BasePresenter<AppInfoModel,AppInfoContract.AppDetailView> {

    @Inject
    public AppDetailPresenter(AppInfoModel mModel, AppInfoContract.AppDetailView mView) {
        super(mModel, mView);
    }

    public void getAppDetail(int id){

        mModel.getAppDetail(id).compose(RxHttpResponseCompat.<AppInfo>compatResult())
                .subscribe(new ProgressDialogSubscriber<AppInfo>(mContext,mView) {
                    @Override
                    public void onNext(AppInfo appInfo) {
                        mView.showAppDetail(appInfo);
                    }
                });


    }

}
