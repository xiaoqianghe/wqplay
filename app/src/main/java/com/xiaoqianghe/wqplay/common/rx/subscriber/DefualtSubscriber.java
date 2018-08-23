package com.xiaoqianghe.wqplay.common.rx.subscriber;


import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author：Wq
 * Date：2017/6/19 14:07
 * Description：//todo
 */

public abstract class DefualtSubscriber<T> implements Observer<T> {

    private final String TAG = this.getClass().getSimpleName();


    @Override
    public void onSubscribe(Disposable d) {


        Log.d(TAG , "Item: onSubscribe");

    }

    @Override
    public void onNext(T t) {
        Log.d(TAG , "Item: onSubscribe");

    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG , "Item: onSubscribe");

    }

    @Override
    public void onComplete() {
        Log.d(TAG , "Item: onSubscribe");

    }
}
