package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.bean.UpLoadRsBean;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.IndexBean;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressDNewDialogSubscriber;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressNewDialogSubscriber;
import com.xiaoqianghe.wqplay.data.DTopListActivityModel;
import com.xiaoqianghe.wqplay.data.TopListActivityModel;
import com.xiaoqianghe.wqplay.di.module.HttpModuleManager;
import com.xiaoqianghe.wqplay.presenter.contract.DTopListActivityContract;
import com.xiaoqianghe.wqplay.presenter.contract.TopListActivityContract;
import com.xiaoqianghe.wqplay.utils.RxUpLoadUtil;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.ObservableTransformer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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




        //@TODO  封装了的网络请求
        mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressDNewDialogSubscriber<IndexBean>(mContext,mView,false,false) {
                    @Override
                    public void onNext(IndexBean indexBean) {
                        mView.showResultData(indexBean);
                    }
                });



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



//        //@TODO  封装了的网络请求(没有使用Dagger2)
//        HttpModuleManager.getInstance().getApiService().index()
//                .compose(RxHttpResponseCompat.<IndexBean>compatResult())
//                .subscribe(new ProgressDNewDialogSubscriber<IndexBean>(mContext,mView) {
//                    @Override
//                    public void onNext(IndexBean indexBean) {
////                        super.onNext(indexBean);
//                        mView.showResultData(indexBean);
//                    }
//                });



//
//        //@TODO 上传 图片
//        HttpModuleManager.getInstance().getApiService().upLoadData(RxUpLoadUtil.createRequestBody(""))
//                .compose(RxHttpResponseCompat.<UpLoadRsBean>compatResult())
//                .subscribe(new ProgressDNewDialogSubscriber<UpLoadRsBean>(mContext,mView) {
//                    @Override
//                    public void onNext(UpLoadRsBean mUpLoadRsBean) {
////                        super.onNext(indexBean);
////                        mView.showResultData(mUpLoadRsBean);
//                    }
//                });


    }



}
