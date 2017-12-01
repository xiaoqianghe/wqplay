package com.xiaoqianghe.wqplay.common.rx;

import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import rx.Observable;

/**
 * Author：Wq
 * Date：2017/11/30 15:21
 * Description：//todo
 */

public class RxSchedulers {

    public static <T> ObservableTransformer<T,T> io_main(){

        return  new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(io.reactivex.Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
