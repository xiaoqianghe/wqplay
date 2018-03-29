package com.xiaoqianghe.wqplay.ui.activity;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.ui.Fragment.BooksTabFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.HomeTabFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.MoviesTabFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.MusicTabFragment;

import java.util.ArrayList;

/**
 * Author：Wq
 * Date：2018/3/14 11:43
 * Description：//todo
 */

public class MainFragmentActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    private ArrayList<Fragment>  fragments;
    private final String TAG="MainFragmentActivity";

    private  HomeTabFragment mHomeTabFragment;
    private BooksTabFragment mBooksTabFragment;
    private MoviesTabFragment  mMoviesTabFragment;
    private MusicTabFragment   mMusicTabFragment;
    private Toolbar toolBar;



    @Override
    protected void init() {

        initBottonNavigationBar();

    }

    private void initBottonNavigationBar() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        toolBar=(Toolbar) findViewById(R.id.toolbar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Home").setActiveColorResource(R.color.md_orange_700))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Books").setActiveColorResource(R.color.md_pink_700))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Music").setActiveColorResource(R.color.md_red_700))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Movies").setActiveColorResource(R.color.md_teal_700))
//                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Games").setActiveColorResource(R.color.md_yellow_700))
                .setFirstSelectedPosition(0)
                .initialise();

//        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        Log.d("======","===setDefaultFragment()");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, HomeTabFragment.newInstance("Home"));
        transaction.commit();

        setTitleBarCenter(Constant.TableType.TAB_HOME);
    }

    private void setTitleBarCenter(String str) {
//        toolBar.setTitle(str);
        toolBar.setSubtitle(str);
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(HomeTabFragment.newInstance(Constant.TableType.TAB_HOME));
        fragments.add(BooksTabFragment.newInstance(Constant.TableType.TAB_BOOKS));
        fragments.add(MusicTabFragment.newInstance(Constant.TableType.TAB_Music));
        fragments.add(MoviesTabFragment.newInstance(Constant.TableType.TAB_MOVIES));
        return fragments;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_mainfragmentactivity;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mHomeTabFragment == null) {
                    mHomeTabFragment = HomeTabFragment.newInstance(Constant.TableType.TAB_HOME);
                }
                transaction.replace(R.id.layFrame, mHomeTabFragment);
                setTitleBarCenter(Constant.TableType.TAB_HOME);
                break;
            case 1:
                if (mBooksTabFragment == null) {
                    mBooksTabFragment= BooksTabFragment.newInstance(Constant.TableType.TAB_BOOKS);
                }
                transaction.replace(R.id.layFrame, mBooksTabFragment);
                setTitleBarCenter(Constant.TableType.TAB_BOOKS);
                break;
            case 2:
                if (mMusicTabFragment == null) {
                    mMusicTabFragment = MusicTabFragment.newInstance(Constant.TableType.TAB_Music);
                }
                transaction.replace(R.id.layFrame, mMusicTabFragment);
                setTitleBarCenter(Constant.TableType.TAB_Music);
                break;
            case 3:
                if (mMoviesTabFragment == null) {
                    mMoviesTabFragment = MoviesTabFragment.newInstance(Constant.TableType.TAB_MOVIES);
                }
                transaction.replace(R.id.layFrame, mMoviesTabFragment);
                setTitleBarCenter(Constant.TableType.TAB_MOVIES);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d("======","===onTabUnselected");
    }

    @Override
    public void onTabReselected(int position) {

    }
}
