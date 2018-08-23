package com.xiaoqianghe.wqplay.common.rx.subscriber;

import android.content.Context;
import android.widget.Toast;

import com.xiaoqianghe.wqplay.common.exception.BaseException;
import com.xiaoqianghe.wqplay.common.util.ProgressDialogHandler;
import com.xiaoqianghe.wqplay.ui.BaseView;
import com.xiaoqianghe.wqplay.ui.DBaseView;

import io.reactivex.disposables.Disposable;

/**
 * Author：Wq
 * Date：2017/6/19 14:19
 * Description：//todo
 */

public abstract class ProgressDNewDialogSubscriber<T> extends ErrHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener {

    private ProgressDialogHandler mProgressDialogHandler;

    private DBaseView mView;

    private Disposable mDisposable;

    private boolean isStartShow=true;//请求开始 是否弹出dialog  默认需要弹窗
    private boolean isEndDismiss=true;//请求完成 是否关闭dialog 默认需要关闭



    public ProgressDNewDialogSubscriber(Context context, DBaseView mView){

        super(context);
        this.mView=mView;
        mProgressDialogHandler=new ProgressDialogHandler(context,true,this);

    }
//
//    public ProgressNewDialogSubscriber(Context context, DBaseView  mView,boolean isStartShow) {
//        super(context);
//        this.mView=mView;
//        this.isStartShow=isStartShow;
//        mProgressDialogHandler=new ProgressDialogHandler(context,true,this);
//    }
//
    public ProgressDNewDialogSubscriber(Context context, DBaseView  mView,boolean isStartShow, boolean isEndDismiss) {
        super(context);
        this.mView=mView;
        this.isStartShow=isStartShow;
        this.isEndDismiss=isEndDismiss;
        mProgressDialogHandler=new ProgressDialogHandler(context,true,this);
    }


    public boolean isShowProgressDialog(){
        return true;
    }



//    @Override
//    public void onStart() {
//       // super.onStart();
//        if(isShowProgressDialog()){
//            //this.mProgressDialogHandler.showProgressDialog();
//            mView.showLoading();
//
//        }
//    }
//
//    @Override
//    public void onCompleted() {
//        if(isShowProgressDialog()){
//           // this.mProgressDialogHandler.dismissProgressDialog();
//            mView.dismissLoading();
//        }
//    }


    @Override
    public void onSubscribe(Disposable d) {
//        super.onSubscribe(d);
        mDisposable = d;
        if(isStartShow){
            showDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
//        super.onError(e);
        dismissDialog();
        BaseException baseException =  mErrorHandler.handleError(e);
        mView.showError(baseException.getDisplayMessage());
    }

    @Override
    public void onComplete() {
        if(isEndDismiss){
            dismissDialog();
        }


        mView.showContent();
    }


    @Override
    public void onNext(T t) {
        if(isEndDismiss){
            dismissDialog();
        }


        if (null == t) {
            Toast.makeText(mContext, "无数据", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    public void onCancelProgress() {
        mDisposable.dispose();
    }


    public void dismissDialog(){
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }

    }

    public void showDialog(){
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.showProgressDialog();
        }

    }

}









