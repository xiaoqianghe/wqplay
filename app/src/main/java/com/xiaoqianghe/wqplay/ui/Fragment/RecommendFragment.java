package com.xiaoqianghe.wqplay.ui.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.IndexBean;
import com.xiaoqianghe.wqplay.di.component.AppComponent;


import com.xiaoqianghe.wqplay.di.component.DaggerRecommendComponent;
import com.xiaoqianghe.wqplay.di.module.RecommendModule;
import com.xiaoqianghe.wqplay.presenter.RecommendPresenter;
import com.xiaoqianghe.wqplay.presenter.contract.AppInfoContract;
import com.xiaoqianghe.wqplay.presenter.contract.RecommendContract;
import com.xiaoqianghe.wqplay.ui.BaseView;
import com.xiaoqianghe.wqplay.ui.adapter.IndexMultipleAdapter;
import com.xiaoqianghe.wqplay.ui.adapter.RecomendAppAdapter;
import com.xiaoqianghe.wqplay.ui.decoration.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：Wq
 * Date：2017/6/7 11:58
 * Description：//todo
 */

public class RecommendFragment extends ProgressDialogFragment<RecommendPresenter> implements AppInfoContract.View  {
    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;
//    private RecomendAppAdapter mAdatper;

    private IndexMultipleAdapter mAdatper;


    @Inject
    ProgressDialog mProgressDialog;

    @Override
    protected void onEmptyViewClick() {
        mPresenter.requestDatas();
    }

    @Override
    protected void init() {

        initRecycleView();
        //获取数据
        mPresenter.requestDatas();
    }

    private void initRecycleView() {

        //为RecyclerView设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }



    @Override
    protected int setLayout() {
        return R.layout.activity_recommend;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
////       DaggerRecommendComponent.builder().appComponent(appComponent).recommendModule(new RecommendModule(this)).build().inject(this);
//        DaggerRecommendComponent.builder().appComponent(appComponent).recommendModule(new RecommendModule(this)).build().inject(this);

//        DaggerRecommendComponent.builder().appComponent(appComponent).recommendModule(new RecommendModule(this)).build().inject(this);

        DaggerRecommendComponent.builder().appComponent(appComponent).recommendModule(new RecommendModule(this)).build().inject(this);



    }

    @Override
    public void showResult(IndexBean indexBean) {

        Log.d(TAG,"showResult::datas::"+new Gson().toJson(indexBean));

        mAdatper = new IndexMultipleAdapter(getActivity());
        mAdatper.setData(indexBean);

        mRecyclerView.setAdapter(mAdatper);

    }

    @Override
    public void onRequestPermissonSuccess() {
        mPresenter.requestDatas();

    }

    @Override
    public void onRequestPermissonError() {
        Toast.makeText(getActivity(),"你已拒绝授权",Toast.LENGTH_LONG).show();

    }




//    @Override
//    public void showNodata() {
//        Log.d(TAG,"=============showNodata()");
//        Toast.makeText(getActivity(), "暂时无数据，请吃完饭再来", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void showResult(List<AppInfo> datas) {
//        Log.d(TAG,"showResult::datas::"+new Gson().toJson(datas));
//        initRecycleView(datas);
//    }
//
//    private void initRecycleView(List<AppInfo> datas) {
//        //为RecyclerView设置布局管理器
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
//        //动画
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mAdatper = new RecomendAppAdapter(datas,getActivity());
//        mRecyclerView.setAdapter(mAdatper);
//    }


}
