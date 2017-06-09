package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.data.RecommedModel;
import com.xiaoqianghe.wqplay.presenter.contract.RecommendContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author：Wq
 * Date：2017/6/1 16:15
 * Description：//todo
 */

public class RecommendPresenter extends BasePresenter<RecommedModel,RecommendContract.View> {


    public RecommendPresenter(RecommedModel mModel, RecommendContract.View mView) {
        super(mModel, mView);
    }

    public void requestDatas(){
        mView.showLoading();
        mModel.getApps(new Callback<PageBean<AppInfo>>(){

            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if(null!=response){
                    mView.showResult(response.body().getDatas());
                }else{
                    mView.showNodata();
                }

                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.dismissLoading();
                mView.showError(t.getMessage());
            }
        });

    }
}
