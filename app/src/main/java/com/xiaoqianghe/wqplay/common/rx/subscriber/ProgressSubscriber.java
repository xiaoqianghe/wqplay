package com.xiaoqianghe.wqplay.common.rx.subscriber;

import android.content.Context;
import android.widget.Toast;

import com.xiaoqianghe.wqplay.common.exception.BaseException;
import com.xiaoqianghe.wqplay.ui.BaseView;

import io.reactivex.disposables.Disposable;

/**
 * Author：Wq
 * Date：2017/11/30 15:44
 * Description：//todo
 */

public abstract class ProgressSubscriber<T> extends ErrHandlerSubscriber<T> {




    private BaseView mView;

    public ProgressSubscriber(Context context,BaseView view) {
        super(context);
        this.mView=view;
    }


    public boolean isShowProgress(){
        return  true;
    }

    @Override
    public void onError(Throwable e) {

//        super.onError(e);
        e.printStackTrace();
        BaseException baseException =  mErrorHandler.handleError(e);
        mView.showError(baseException.getDisplayMessage());
    }

    @Override
    public void onComplete() {
        mView.dismissLoading();
    }

    @Override
    public void onSubscribe(Disposable d) {
//        super.onSubscribe(d);
        if(isShowProgress()){
            mView.showLoading();
        }
    }

    @Override
    public void onNext(T t) {
        if (null == t) {
            Toast.makeText(mContext, "无数据", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
