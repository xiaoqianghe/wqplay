package com.xiaoqianghe.wqplay.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.presenter.TestPresenter;
import com.xiaoqianghe.wqplay.presenter.contract.TestContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：Wq
 * Date：2018/7/17 17:19
 * Description：//todo
 */

public class TestActivity extends BaseActivity<TestPresenter> implements TestContract.TestView {
    @BindView(R.id.bt_totest)
    Button btTotest;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    @Override
    protected void init() {

        initView();




    }

    private void initView() {






    }

    @Override
    protected int setLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {










    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(String str) {

    }

    @Override
    public void loadDataSuccess() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
