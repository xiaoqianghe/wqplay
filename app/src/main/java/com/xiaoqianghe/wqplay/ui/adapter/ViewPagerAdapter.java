package com.xiaoqianghe.wqplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.xiaoqianghe.wqplay.ui.Fragment.CategoryFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.GamesFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.GuiderFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.RankingFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.RecommendFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.RecommendProFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.TopListFragment;
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
    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
        initFragmentList();
    }

    private void initFragmentList() {
        mFragmentList.add(new FragmentInfo("篮球",RecommendFragment.class));
//        mFragmentList.add(new FragmentInfo("篮球Pro",RecommendProFragment.class));
//        mFragmentList.add(new FragmentInfo("足球",RankingFragment.class));
        mFragmentList.add(new FragmentInfo("topList", TopListFragment.class));
        mFragmentList.add(new FragmentInfo("乒乓球",GamesFragment.class));
        mFragmentList.add(new FragmentInfo("羽毛球",CategoryFragment.class));
        mFragmentList.add(new FragmentInfo("橄榄球",GuiderFragment.class));
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
