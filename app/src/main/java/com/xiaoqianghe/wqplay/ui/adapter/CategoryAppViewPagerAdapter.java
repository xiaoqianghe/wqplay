package com.xiaoqianghe.wqplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Wq
 * Date：2017/9/19 11:50
 * Description：//todo
 */

public class CategoryAppViewPagerAdapter  extends FragmentStatePagerAdapter {


    private List<String> titles=new ArrayList<String>();

    private int mCategoryId;

    public CategoryAppViewPagerAdapter(FragmentManager fm, int mCategoryId) {
        super(fm);
        this.mCategoryId = mCategoryId;

        titles.add("精品");
        titles.add("排行");
        titles.add("新评");
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
