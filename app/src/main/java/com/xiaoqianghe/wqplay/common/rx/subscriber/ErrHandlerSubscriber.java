package com.xiaoqianghe.wqplay.common.rx.subscriber;

import android.content.Context;
import android.util.Log;

import com.xiaoqianghe.wqplay.common.exception.BaseException;
import com.xiaoqianghe.wqplay.common.rx.RxErrHandler;

/**
 * Author：Wq
 * Date：2017/6/19 14:09
 * Description：//todo
 */

public abstract class ErrHandlerSubscriber<T> extends DefualtSubscriber<T>{

    protected RxErrHandler mErrorHandler=null;
    protected Context mContext;


    public ErrHandlerSubscriber(Context context) {
        this.mContext=context;
        mErrorHandler=new RxErrHandler(mContext);

    }

    @Override
    public void onError(Throwable e) {
        BaseException baseException=mErrorHandler.handleError(e);
        if(null==baseException){
            e.printStackTrace();
            Log.d("ErrorHandlerSubscriber",e.getMessage());

        }else{
            mErrorHandler.showErrorMessage(baseException);

        }
    }
}
