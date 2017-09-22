package com.xiaoqianghe.wqplay.common.rx;


import android.util.Log;

import com.google.gson.Gson;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.common.exception.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author：Wq
 * Date：2017/6/16 15:02
 * Description：//todo
 */

public class RxHttpResponseCompat {

    private final String TAG = this.getClass().getSimpleName();

    public static <T> Observable.Transformer<BaseBean<T>,T>  compatResult(){

        return new Observable.Transformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {
                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(final BaseBean<T> tBaseBean) {

                        Log.d("RxHttpResponseCompat","======tBaseBean::"+new Gson().toJson(tBaseBean));
                        if(tBaseBean.SUCCESS()){
                            return Observable.create(new Observable.OnSubscribe<T>() {
                                @Override
                                public void call(Subscriber<? super T> subscriber) {
                                    subscriber.onNext(tBaseBean.getData());
                                    subscriber.onCompleted();
                                }
                            });

                        }else{
                            //{"status":10010,"message":"token 丢失"}
                            ApiException mApiException=new ApiException(tBaseBean.getStatus(),tBaseBean.getMessage());
                            Log.d("RxHttpResponseCompat","======mApiException::"+new Gson().toJson(mApiException));
                            return Observable.error(mApiException);
                        }

                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };

    }


}
