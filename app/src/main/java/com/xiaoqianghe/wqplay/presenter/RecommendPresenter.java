package com.xiaoqianghe.wqplay.presenter;

import android.support.annotation.MainThread;
import android.util.Log;

import com.google.gson.Gson;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.data.RecommedModel;
import com.xiaoqianghe.wqplay.presenter.contract.RecommendContract;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author：Wq
 * Date：2017/6/1 16:15
 * Description：//todo
 */

public class RecommendPresenter extends BasePresenter<RecommedModel,RecommendContract.View> {

    private final String TAG = this.getClass().getSimpleName();

    @Inject
    public RecommendPresenter(RecommedModel mModel, RecommendContract.View mView) {
        super(mModel, mView);
    }

    public void requestDatas(){
 //       mView.showLoading();
        //OkHttp中的的网络请求
//        mModel.getApps(new Callback<PageBean<AppInfo>>(){
//
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//                if(null!=response){
//                    mView.showResult(response.body().getDatas());
//                }else{
//                    mView.showNodata();
//                }
//
//                mView.dismissLoading();
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//                mView.dismissLoading();
//                mView.showError(t.getMessage());
//            }
//        });



        //RxJava+Retrofit的网络请求的整合
//        mModel.getApps()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<BaseBean<PageBean<AppInfo>>>() {
//
//            @Override
//            public void onStart() {
//                super.onStart();
//                mView.showLoading();
//            }
//
//            @Override
//            public void onCompleted() {
//                mView.dismissLoading();
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                mView.dismissLoading();
//
//            }
//
//            @Override
//            public void onNext(BaseBean<PageBean<AppInfo>> pageBeanBaseBean) {
//                Log.d(TAG,"===========:"+new Gson().toJson(pageBeanBaseBean));
//                if(null!=pageBeanBaseBean){
//                    PageBean<AppInfo> mPageBean=(PageBean<AppInfo>)pageBeanBaseBean.getData();
//                    mView.showResult(mPageBean.getDatas());
//                }else{
//                    mView.showNodata();
//                }
//
//                mView.dismissLoading();
//
//            }
//        });

        // 预先处理
        mView.showLoading();

        mModel.getApps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new Subscriber<PageBean<AppInfo>>() {
                    @Override
                    public void onCompleted() {
                        mView.dismissLoading();

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoading();

                    }

                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        Log.d(TAG,"===========:"+new Gson().toJson(appInfoPageBean));
                        if(null!=appInfoPageBean){
                            mView.showResult(appInfoPageBean.getDatas());
                        }else{
                            mView.showNodata();
                        }
                            mView.dismissLoading();

                    }
                });





    }
}
