package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.presenter.contract.RecommendContract;

/**
 * Author：Wq
 * Date：2017/6/1 16:15
 * Description：//todo
 */

public class RecommendPresenter implements RecommendContract.Presenter {


    private RecommendContract.View mView;
    public RecommendPresenter(RecommendContract.View mView) {
        this.mView=mView;
    }

    @Override
    public void requestData() {
        //获取网络数据



    }
}
