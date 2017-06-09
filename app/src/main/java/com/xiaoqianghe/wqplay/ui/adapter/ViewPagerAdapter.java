package com.xiaoqianghe.wqplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.xiaoqianghe.wqplay.ui.Fragment.RecommendFragment;
import com.xiaoqianghe.wqplay.ui.bean.FragmentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Wq
 * Date：2017/6/9 16:38
 * Description：//todo
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    private List<FragmentInfo>  mFragmentList= new ArrayList<FragmentInfo>();
//    public ViewPagerAdapter(FragmentManager fragmentManager) {
//        super(fragmentManager);
//    }

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
        initFragmentList();
    }


    private void initFragmentList() {

        mFragmentList.add(new FragmentInfo("推荐",RecommendFragment.class));
        mFragmentList.add(new FragmentInfo("排行",RecommendFragment.class));
        mFragmentList.add(new FragmentInfo("游戏",RecommendFragment.class));
        mFragmentList.add(new FragmentInfo("分类",RecommendFragment.class));
        mFragmentList.add(new FragmentInfo("推荐",RecommendFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment)mFragmentList.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
        return mFragmentList.get(position).getTitle();
    }
}
