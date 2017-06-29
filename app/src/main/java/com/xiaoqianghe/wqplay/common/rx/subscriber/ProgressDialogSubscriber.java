package com.xiaoqianghe.wqplay.common.rx.subscriber;

import android.content.Context;

import com.xiaoqianghe.wqplay.common.exception.BaseException;
import com.xiaoqianghe.wqplay.common.util.ProgressDialogHandler;
import com.xiaoqianghe.wqplay.common.util.ProgressDialogHandler.OnProgressCancelListener;
import com.xiaoqianghe.wqplay.presenter.contract.RecommendContract;
import com.xiaoqianghe.wqplay.ui.BaseView;

/**
 * Author：Wq
 * Date：2017/6/19 14:19
 * Description：//todo
 */

public abstract class ProgressDialogSubscriber<T> extends ErrHandlerSubscriber<T> {

    private ProgressDialogHandler mProgressDialogHandler;

    private BaseView mView;

//    public ProgressDialogSubscriber(Context context){
//
//        super(context);
//        mProgressDialogHandler=new ProgressDialogHandler(this.mContext,true,this);
//
//    }

    public ProgressDialogSubscriber(Context mContext, BaseView  mView) {
        super(mContext);
        this.mView=mView;
    }


    public boolean isShowProgressDialog(){
        return true;
    }

    @Override
    public void onStart() {
       // super.onStart();
        if(isShowProgressDialog()){
            //this.mProgressDialogHandler.showProgressDialog();
            mView.showLoading();

        }
    }

    @Override
    public void onCompleted() {
        if(isShowProgressDialog()){
           // this.mProgressDialogHandler.dismissProgressDialog();
            mView.dismissLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
//        if(isShowProgressDialog()){
//            this.mProgressDialogHandler.dismissProgressDialog();
//        }
        BaseException baseException =  mErrorHandler.handleError(e);
        mView.showError(baseException.getDisplayMessage());


    }



//    @Override
//    public void onNext(T t) {
//        if(isShowProgressDialog()){
//            this.mProgressDialogHandler.dismissProgressDialog();
//        }
//
//    }
//
//    @Override
//    public void onCancelProgress() {
//        unsubscribe();
//
//    }
}


