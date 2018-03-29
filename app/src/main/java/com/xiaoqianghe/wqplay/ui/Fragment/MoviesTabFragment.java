package com.xiaoqianghe.wqplay.ui.Fragment;

import android.os.Bundle;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.di.component.AppComponent;

/**
 * Author：Wq
 * Date：2018/3/14 16:19
 * Description：//todo
 */

public class MoviesTabFragment extends BaseFragment {
    @Override
    public void showError(String str) {

    }

    @Override
    protected void init() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_movies_tab;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    public static MoviesTabFragment newInstance() {

        Bundle args = new Bundle();

        MoviesTabFragment fragment = new MoviesTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static MoviesTabFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("args1",str);

        MoviesTabFragment fragment = new MoviesTabFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
