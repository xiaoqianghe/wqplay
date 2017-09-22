package com.xiaoqianghe.wqplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.requestbean.Category;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.ui.adapter.CategoryAppViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Author：Wq
 * Date：2017/9/18 15:25
 * Description：//todo
 */

public class CategoryAppActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.activity_cateogry_app)
    LinearLayout activityCateogryApp;

    private Category category;

    @Override
    protected void init() {
        getData();
        initTableLayout();

    }

    private void initTableLayout() {

        toolBar.setTitle(category.getName());

        toolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );

        CategoryAppViewPagerAdapter adapter = new CategoryAppViewPagerAdapter(getSupportFragmentManager(),category.getId());
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }

    private void getData() {

        Intent intent = getIntent();

        category = (Category) intent.getSerializableExtra(Constant.CATEGORY);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_cateogry_app;
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
}
