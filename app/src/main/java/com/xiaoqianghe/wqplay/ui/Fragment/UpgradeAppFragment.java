package com.xiaoqianghe.wqplay.ui.Fragment;

import android.support.v7.widget.RecyclerView;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.ui.adapter.AppInfoAdapter;

import java.util.List;

/**
 * Author：Wq
 * Date：2017/12/8 15:59
 * Description：//todo
 */

public class UpgradeAppFragment extends AppManangerFragment{

    AppInfoAdapter mAdapter;

    @Override
    protected void init() {
        super.init();

        mPresenter.getUpdateApps();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = AppInfoAdapter.builder().updateStatus(true).rxDownload(mPresenter.getRxDowanload()).build();

        return mAdapter;
    }



    @Override
    protected void onEmptyViewClick() {

    }

    @Override
    public void showUpdateApps(List<AppInfo> appInfos) {

        mAdapter.addData(appInfos);

    }
}
