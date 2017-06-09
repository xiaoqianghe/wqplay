package com.xiaoqianghe.wqplay.ui.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.ui.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：Wq
 * Date：2017/6/1 16:00
 * Description：//todo
 */

public class MainActivity extends BaseActivity {


//    @BindView(R.id.view_pager)
//    ViewPager viewPager;
//    @BindView(R.id.drawer_layout)
//    LinearLayout drawerLayout;

    private ViewPager viewPager;

    @Override
    protected void init() {
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        initDrawerLayout();
        initTablayout();
    }

    private void initTablayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private void initDrawerLayout() {

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
