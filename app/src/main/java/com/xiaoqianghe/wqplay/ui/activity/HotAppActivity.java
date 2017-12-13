package com.xiaoqianghe.wqplay.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.GenericFont;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.common.font.WqplayFont;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.ui.Fragment.HotAppFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：Wq
 * Date：2017/12/12 17:05
 * Description：//todo
 */

public class HotAppActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.content_layout)
    FrameLayout contentLayout;
    @BindView(R.id.activity_hot_app)
    LinearLayout activityHotApp;

    @Override
    protected void init() {


        toolBar.setNavigationIcon(new IconicsDrawable(this)
        .icon(Ionicons.Icon.ion_ios_arrow_back)
        .sizeDp(16)
        .color(getResources().getColor(R.color.md_white_1000)));

        toolBar.setTitle("热门应用");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        showFragment();




    }

    private void showFragment() {

   FragmentTransaction  ft= getSupportFragmentManager().beginTransaction();

        HotAppFragment fragment=new HotAppFragment();

        ft.add(R.id.content_layout,fragment);
        ft.commit();





    }

    public HotAppActivity() {
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_hot_app;
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
