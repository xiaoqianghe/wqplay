package com.xiaoqianghe.wqplay.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.requestbean.IndexBean;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.di.component.DaggerTopListActivityComponent;
import com.xiaoqianghe.wqplay.di.module.TopListActivityModule;
import com.xiaoqianghe.wqplay.presenter.TopListActivityPresenter;
import com.xiaoqianghe.wqplay.presenter.contract.TopListActivityContract;
import com.xiaoqianghe.wqplay.ui.adapter.IndexMultipleAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：Wq
 * Date：2018/8/16 11:32
 * Description：//todo
 */

public class TopListActivity extends BaseStatusActivity<TopListActivityPresenter> implements TopListActivityContract.ITestListActivityView {


    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private IndexMultipleAdapter mAdatper;

    @Override
    protected void init() {
        initRecycleView();
        mPresenter.requestData();
    }




    private void initRecycleView() {
        //为RecyclerView设置布局管理器
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        //动画
        recycleView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    protected int setLayout() {
        return R.layout.activity_toplistactivity;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerTopListActivityComponent.builder().appComponent(appComponent).topListActivityModule(new TopListActivityModule(this)).build().inject(this);
    }

    @Override
    protected void onEmptyViewClick() {
        mPresenter.requestData();
    }

    @Override
    public void showResultData(IndexBean indexBean) {
//        Log.d(TAG,"showResult::datas::"+new Gson().toJson(indexBean));
        mAdatper = new IndexMultipleAdapter(this);
        mAdatper.setData(indexBean);
        recycleView.setAdapter(mAdatper);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
