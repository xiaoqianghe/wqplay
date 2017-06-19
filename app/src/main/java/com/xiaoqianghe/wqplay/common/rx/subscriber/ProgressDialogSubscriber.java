package com.xiaoqianghe.wqplay.common.rx.subscriber;

import android.content.Context;

import com.xiaoqianghe.wqplay.common.util.ProgressDialogHandler;
import com.xiaoqianghe.wqplay.common.util.ProgressDialogHandler.OnProgressCancelListener;

/**
 * Author：Wq
 * Date：2017/6/19 14:19
 * Description：//todo
 */

public class ProgressDialogSubscriber<T> extends ErrHandlerSubscriber<T> implements OnProgressCancelListener{

    private ProgressDialogHandler mProgressDialogHandler;

    public ProgressDialogSubscriber(Context context){

        super(context);
        mProgressDialogHandler=new ProgressDialogHandler(this.mContext,true,this);

    }


    public boolean isShowProgressDialog(){
        return true;
    }

    @Override
    public void onStart() {
       // super.onStart();
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.showProgressDialog();

        }
    }

    @Override
    public void onCompleted() {
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();

        }

    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);

        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }

    }

    @Override
    public void onNext(T t) {
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }

    }

    @Override
    public void onCancelProgress() {
        unsubscribe();

    }
}


