package com.xiaoqianghe.wqplay.ui.Fragment;

import android.os.Bundle;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.di.component.AppComponent;

/**
 * Author：Wq
 * Date：2018/3/14 16:19
 * Description：//todo
 */

public class HomeTabFragment extends BaseFragment {
    @Override
    public void showError(String str) {


    }

    @Override
    protected void init() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_home_tab;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    public static HomeTabFragment newInstance() {

        Bundle args = new Bundle();


        HomeTabFragment fragment = new HomeTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static HomeTabFragment newInstance(String str) {
        Bundle args = new Bundle();
        args.putString("agrs1", str);

        HomeTabFragment fragment = new HomeTabFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
