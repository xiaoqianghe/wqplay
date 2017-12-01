package com.xiaoqianghe.wqplay.common.rx;


import android.util.Log;

import com.google.gson.Gson;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.common.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：Wq
 * Date：2017/6/16 15:02
 * Description：//todo
 */

public class RxHttpResponseCompat {

    private final String TAG = this.getClass().getSimpleName();





    public static <T> ObservableTransformer<BaseBean<T>,T> compatResult(){


        return  new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> baseBeanObservable) {

                return baseBeanObservable.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull final BaseBean<T> tBaseBean) throws Exception {

                        if(tBaseBean.SUCCESS()){


                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> subscriber) throws Exception {

                                    try {
                                        subscriber.onNext(tBaseBean.getData());
                                        subscriber.onComplete();
                                    }
                                    catch (Exception e){
                                        subscriber.onError(e);
                                    }
                                }
                            });
                        }
                        else {
                            return  Observable.error(new ApiException(tBaseBean.getStatus(),tBaseBean.getMessage()));
                        }

                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };

    }

//    public static <T> Observable.Transformer<BaseBean<T>,T>  compatResult(){
//
//        return new Observable.Transformer<BaseBean<T>, T>() {
//            @Override
//            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {
//                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
//                    @Override
//                    public Observable<T> call(final BaseBean<T> tBaseBean) {
//
//                        Log.d("RxHttpResponseCompat","======tBaseBean::"+new Gson().toJson(tBaseBean));
//                        if(tBaseBean.SUCCESS()){
//                            return Observable.create(new Observable.OnSubscribe<T>() {
//                                @Override
//                                public void call(Subscriber<? super T> subscriber) {
//                                    subscriber.onNext(tBaseBean.getData());
//                                    subscriber.onCompleted();
//                                }
//                            });
//
//                        }else{
//                            //{"status":10010,"message":"token 丢失"}
//                            ApiException mApiException=new ApiException(tBaseBean.getStatus(),tBaseBean.getMessage());
//                            Log.d("RxHttpResponseCompat","======mApiException::"+new Gson().toJson(mApiException));
//                            return Observable.error(mApiException);
//                        }
//
//                    }
//                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
//            }
//        };
//
//    }


}
