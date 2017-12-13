package com.xiaoqianghe.wqplay.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.requestbean.User;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.common.font.WqplayFont;
import com.xiaoqianghe.wqplay.common.imageloader.GlideCircleTransform;
import com.xiaoqianghe.wqplay.common.rx.RxBus;
import com.xiaoqianghe.wqplay.common.util.ACache;
import com.xiaoqianghe.wqplay.common.util.PermissionUtil;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.di.component.DaggerMainComponent;
import com.xiaoqianghe.wqplay.di.module.MainModule;
import com.xiaoqianghe.wqplay.presenter.MainPresenter;
import com.xiaoqianghe.wqplay.presenter.contract.MainContract;
import com.xiaoqianghe.wqplay.ui.Fragment.CategoryFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.GamesFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.RecommendFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.TopListFragment;
import com.xiaoqianghe.wqplay.ui.adapter.ViewPagerAdapter;
import com.xiaoqianghe.wqplay.ui.bean.FragmentInfo;
import com.xiaoqianghe.wqplay.ui.widget.BadgeActionProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * Author：Wq
 * Date：2017/6/1 16:00
 * Description：//todo
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView{
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


    private  BadgeActionProvider badgeActionProvider;

    @Override
    protected void init() {

        boolean key_smart_install= getSharedPreferences(getPackageName()+"_preferences",MODE_PRIVATE).getBoolean("key_smart_install",false);

        Log.d("MainActivity","key_smart_install="+key_smart_install);




        RxBus.getDefault().toObservable(User.class).subscribe(new Consumer<User>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull User user) throws Exception {
                initUserHeadView(user);
            }
        });


//        PermissionUtil.requestPermisson(this,Manifest.permission.READ_PHONE_STATE).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(@io.reactivex.annotations.NonNull Boolean aBoolean) throws Exception {
//                if(aBoolean){
//                    initDrawerLayout();
//
//                    initTablayout();
//
//                    initUser();
//                }else{
//
//                }
//            }
//        });


        mPresenter.requestPermission();

        mPresenter.getAppUpdateInfo();

//        viewPager = (ViewPager) findViewById(R.id.view_pager);
//        initDrawerLayout();
//        initTablayout();
    }

    private void initTablayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),initFragments());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        tabLayout.setupWithViewPager(viewPager);
    }


    private List<FragmentInfo> initFragments(){

        List<FragmentInfo> mFragments = new ArrayList<>(4);

        mFragments.add(new FragmentInfo("推荐",RecommendFragment.class));
        mFragments.add(new FragmentInfo ("排行", TopListFragment.class));


        mFragments.add(new FragmentInfo ("游戏", GamesFragment.class));
        mFragments.add(new FragmentInfo ("分类", CategoryFragment.class));

        return  mFragments;

    }


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {





        DaggerMainComponent.builder().appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);








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
                    case R.id.menu_logout:

                        logout();

                        break;


                    case R.id.menu_download_manager:

                        toAppManagerActivity(1);

                        break;
                    case R.id.menu_app_uninstall:

                        toAppManagerActivity(3);

                        break;
                    case R.id.menu_app_update:

                        toAppManagerActivity(2);

                        break;
                    case R.id.menu_setting:

                        startActivity(new Intent(MainActivity.this,SettingActivity.class));

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

    private void initUserHeadView(User user){

        Glide.with(this).load(user.getLogo_url()).transform(new GlideCircleTransform(this)).into(mUserHeadView);

        mTextUserName.setText(user.getUsername());
    }


    private void initUser(){

        Object objUser= ACache.get(this).getAsObject(Constant.USER);

        if(objUser ==null){

            headerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
            });

        }
        else{

            User user = (User) objUser;
            initUserHeadView(user);

        }


    }


    @Override
    public void requestPermissonSuccess() {

        Toast.makeText(MainActivity.this,"授权成功....",Toast.LENGTH_LONG).show();


        initToolbar();
        initDrawerLayout();
        initTablayout();
        initUser();

    }

    private void initToolbar() {



        toolBar.inflateMenu(R.menu.toolbar_menu);

        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId() == R.id.action_search){

                    startActivity(new Intent(MainActivity.this,SearchActivity.class));
                }

                return true;
            }
        });


        MenuItem downloadMenuItem = toolBar.getMenu().findItem(R.id.action_download);


        badgeActionProvider = (BadgeActionProvider) MenuItemCompat.getActionProvider(downloadMenuItem);

        badgeActionProvider.setIcon(DrawableCompat.wrap(new IconicsDrawable(this, WqplayFont.Icon.cniao_download).color(ContextCompat.getColor(this,R.color.white))));

        badgeActionProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toAppManagerActivity(badgeActionProvider.getBadgeNum()>0?2:0);


            }
        });
    }



    private void toAppManagerActivity(int position){

        Intent intent = new Intent(MainActivity.this,AppManagerActivity.class);

        intent.putExtra(Constant.POSITION,position);

        startActivity(new Intent(intent));

    }

    @Override
    public void requestPermissonFail() {


        Toast.makeText(MainActivity.this,"授权失败....",Toast.LENGTH_LONG).show();

    }

    @Override
    public void changeAppNeedUpdateCount(int count) {

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
}
