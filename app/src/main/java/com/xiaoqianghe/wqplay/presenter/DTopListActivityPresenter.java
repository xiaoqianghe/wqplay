package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.bean.requestbean.IndexBean;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressDNewDialogSubscriber;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressNewDialogSubscriber;
import com.xiaoqianghe.wqplay.data.DTopListActivityModel;
import com.xiaoqianghe.wqplay.data.TopListActivityModel;
import com.xiaoqianghe.wqplay.di.module.HttpModuleManager;
import com.xiaoqianghe.wqplay.presenter.contract.DTopListActivityContract;
import com.xiaoqianghe.wqplay.presenter.contract.TopListActivityContract;

import javax.inject.Inject;

/**
 * Author：Wq
 * Date：2018/8/16 11:41
 * Description：//todo
 */

public class DTopListActivityPresenter extends DBasePresenter<DTopListActivityModel,DTopListActivityContract.DTopListActivityView> {

    @Inject
    public DTopListActivityPresenter(DTopListActivityModel mModel, DTopListActivityContract.DTopListActivityView mView) {
        super(mModel, mView);
    }


    public void requestData(){

//        mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult())
//                .subscribe(new ProgressSubscriber<IndexBean>(mContext,mView) {
//                    @Override
//                    public void onNext(IndexBean indexBean) {
//                        mView.showResultData(indexBean);
//                    }
//                });
//



//
//        //@TODO  封装了的网络请求
//        mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult())
//                .subscribe(new ProgressDNewDialogSubscriber<IndexBean>(mContext,mView,false,false) {
//                    @Override
//                    public void onNext(IndexBean indexBean) {
//                        mView.showResultData(indexBean);
//                    }
//                });



//        //@TODO  封装了的网络请求(没有使用Dagger2)
//      HttpModuleManager.getInstance().getApiService().index()
//              .compose(RxHttpResponseCompat.<IndexBean>compatResult())
//              .subscribe(new ProgressDNewDialogSubscriber<IndexBean>(mContext,mView,true,false) {
//                    @Override
//                    public void onNext(IndexBean indexBean) {
////                        super.onNext(indexBean);
//                        mView.showResultData(indexBean);
//                    }
//                });



        //@TODO  封装了的网络请求(没有使用Dagger2)
        HttpModuleManager.getInstance().getApiService().index()
                .compose(RxHttpResponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressDNewDialogSubscriber<IndexBean>(mContext,mView) {
                    @Override
                    public void onNext(IndexBean indexBean) {
//                        super.onNext(indexBean);
                        mView.showResultData(indexBean);
                    }
                });


    }


}
