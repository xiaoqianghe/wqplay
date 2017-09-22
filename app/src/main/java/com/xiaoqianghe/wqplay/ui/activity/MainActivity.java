package com.xiaoqianghe.wqplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.common.font.WqplayFont;
import com.xiaoqianghe.wqplay.common.util.ACache;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.ui.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：Wq
 * Date：2017/6/1 16:00
 * Description：//todo
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private View headerView;




    private ImageView mUserHeadView;
    private TextView mTextUserName;

    @Override
    protected void init() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        initDrawerLayout();
        initTablayout();
    }

    private void initTablayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
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


    private void initDrawerLayout() {


        headerView = navigationView.getHeaderView(0);


        mUserHeadView = (ImageView) headerView.findViewById(R.id.img_avatar);


        mUserHeadView.setImageDrawable(new IconicsDrawable(this, WqplayFont.Icon.cniao_head).colorRes(R.color.white));

        mTextUserName = (TextView) headerView.findViewById(R.id.txt_username);




        navigationView.getMenu().findItem(R.id.menu_app_update).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_loop));
        navigationView.getMenu().findItem(R.id.menu_download_manager).setIcon(new IconicsDrawable(this, WqplayFont.Icon.cniao_download));
        navigationView.getMenu().findItem(R.id.menu_app_uninstall).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline));
        navigationView.getMenu().findItem(R.id.menu_setting).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_gear_outline));
        navigationView.getMenu().findItem(R.id.menu_logout).setIcon(new IconicsDrawable(this, WqplayFont.Icon.cniao_shutdown));







        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "headerView clicked", Toast.LENGTH_LONG).show();
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {

                    case R.id.menu_app_update:

                        Toast.makeText(MainActivity.this, "点击了应用更新", Toast.LENGTH_LONG).show();

                        break;


//                    case R.id.menu_message:
//
//                        Toast.makeText(MainActivity.this, "点击了消息", Toast.LENGTH_LONG).show();
//
//                        break;

                    case R.id.menu_logout:

                        logout();

                        break;

                }


                return false;
            }
        });


        toolBar.inflateMenu(R.menu.toolbar_menu);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolBar, R.string.open, R.string.close);

        drawerToggle.syncState();

        drawerLayout.addDrawerListener(drawerToggle);


    }


    private void logout() {

        ACache aCache = ACache.get(this);

        aCache.put(Constant.TOKEN,"");
        aCache.put(Constant.USER,"");

        mUserHeadView.setImageDrawable(new IconicsDrawable(this, WqplayFont.Icon.cniao_head).colorRes(R.color.white));
        mTextUserName.setText("未登录");

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

        Toast.makeText(MainActivity.this,"您已退出登录",Toast.LENGTH_LONG).show();
    }



}
