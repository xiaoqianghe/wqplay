package com.xiaoqianghe.wqplay.ui.Fragment;

import android.os.Bundle;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.di.component.AppComponent;

/**
 * Author：Wq
 * Date：2018/3/14 16:19
 * Description：//todo
 */

public class MusicTabFragment extends BaseFragment {
    @Override
    public void showError(String str) {

    }

    @Override
    protected void init() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_musics_tab;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    public static MusicTabFragment newInstance() {

        Bundle args = new Bundle();

        MusicTabFragment fragment = new MusicTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public static MusicTabFragment newInstance(String str) {

        Bundle args = new Bundle();

        args.putString("args1",str);

        MusicTabFragment fragment = new MusicTabFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
