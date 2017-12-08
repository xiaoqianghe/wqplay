package com.xiaoqianghe.wqplay.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.ui.Fragment.DownloadedFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.DownloadingFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.InstalledAppAppFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.UpgradeAppFragment;
import com.xiaoqianghe.wqplay.ui.adapter.ViewPagerAdapter;
import com.xiaoqianghe.wqplay.ui.bean.FragmentInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：Wq
 * Date：2017/12/1 17:51
 * Description：//todo
 */

public class AppManagerActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.activity_download_manager)
    LinearLayout activityDownloadManager;

    private  int position;

    @Override
    protected void init() {


        position = getIntent().getIntExtra(Constant.POSITION,0);
        initToolbar();
        initTablayout();

    }

    private void initToolbar() {
    }


    private void initTablayout() {




        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),initFragments());
        viewpager.setOffscreenPageLimit(adapter.getCount());
        viewpager.setAdapter(adapter);


        tabs.setupWithViewPager(viewpager);

        viewpager.setCurrentItem(position);
        tabs.getTabAt(position).select();


    }

    @Override
    protected int setLayout() {
        return R.layout.activity_download_manager;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



    private List<FragmentInfo> initFragments(){

        List<FragmentInfo> mFragments = new ArrayList<>(4);

        mFragments.add(new FragmentInfo("下载",DownloadingFragment.class));
        mFragments.add(new FragmentInfo ("已完成", DownloadedFragment.class));
        mFragments.add(new FragmentInfo ("升级", UpgradeAppFragment.class));

        mFragments.add(new FragmentInfo ("已经安装",InstalledAppAppFragment.class));


        return  mFragments;

    }
}
