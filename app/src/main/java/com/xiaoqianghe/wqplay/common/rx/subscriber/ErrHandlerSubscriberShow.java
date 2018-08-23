package com.xiaoqianghe.wqplay.common.rx.subscriber;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.common.exception.BaseException;
import com.xiaoqianghe.wqplay.common.rx.RxErrHandler;
import com.xiaoqianghe.wqplay.widget.dialog.LoadingDialog;

import io.reactivex.disposables.Disposable;

/**
 * Author：Wq
 * Date：2017/6/19 14:09
 * Description：//todo
 */

public abstract class ErrHandlerSubscriberShow<T> extends DefualtSubscriber<T>{

    protected RxErrHandler mErrorHandler=null;
    protected Context mContext;

    protected int whatRequest=0;

    protected boolean isShowDialog=false;


    protected LoadingDialog mDialog;



    public ErrHandlerSubscriberShow(Context context,int whatRequest,boolean isShowDialog) {
        this.mContext=context;
        this.whatRequest=whatRequest;
        this.isShowDialog=isShowDialog;

        mErrorHandler=new RxErrHandler(mContext);


        if (isShowDialog) {
            mDialog = (LoadingDialog) LoadingDialog.init()
                    .setMargin(30)
                    .setDimAmount(0.3f)
                    .setAnimStyle(R.style.DialoLoadinggAnimation);
//            mDialog.setTitleText("正在加载中...");
            mDialog.setCancelable(false);
        }

    }

    public ErrHandlerSubscriberShow(Context context) {
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

            //@// TODO: 2017/11/30  这里需要判断是否是Token 失效的异常 如果是 则需要跳转 登录页面

        }
        onError(whatRequest,e);

    }

    @Override
    public void onSubscribe(Disposable d) {


//        mRxManager.add(mKey, d);
        if (isShowDialog && null != mDialog) {
            mDialog.show(((AppCompatActivity) mContext).getSupportFragmentManager());
        }
        onStart(whatRequest);



    }

    @Override
    public void onNext(T t) {


        if (null == t) {
            Toast.makeText(mContext, "无数据", Toast.LENGTH_SHORT).show();
            return;
        }
        onSuccess(whatRequest,t);

    }

    @Override
    public void onComplete() {
        if (isShowDialog && null != mDialog && null != mDialog.getDialog() && mDialog.getDialog().isShowing()) {
            mDialog.dismiss();
        }
    }

    public abstract  void onError(int whatRequest, Throwable e);
    public abstract  void onSuccess(int whatRequest,T t);

    public void onStart(int whichRequest) {
//        if (!NetworkUtil.isNetworkAvailable(context)) {
//        if (!NetworkUtils.isAvailableByPing()) {
//            Toast.show("当前网络不可用，请检查网络情况");
////            Toast.makeText(App.getApplication(), "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();
////            NetworkUtils.openWirelessSettings();
////            ActivityManager.getInstance().getCurrentActivity().startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
////            if (isNeedCahe) {
////                //无网络已经读取缓存
////            }
//            // **一定要主动调用下面这一句**
//            onComplete();
//            return;
//        }
        // 显示进度条
        if (isShowDialog && null != mDialog && null != mDialog.getDialog() && !mDialog.getDialog().isShowing()) {
            mDialog.show(((AppCompatActivity) mContext).getSupportFragmentManager());
        }

    }

}
