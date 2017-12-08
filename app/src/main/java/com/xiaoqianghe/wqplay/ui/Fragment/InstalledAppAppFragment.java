package com.xiaoqianghe.wqplay.ui.Fragment;

import android.support.v7.widget.RecyclerView;

import com.xiaoqianghe.wqplay.common.apkparset.AndroidApk;
import com.xiaoqianghe.wqplay.ui.adapter.AndroidApkAdapter;

import java.util.List;

/**
 * Author：Wq
 * Date：2017/12/8 16:14
 * Description：//todo
 */

public class InstalledAppAppFragment extends AppManangerFragment {


    private AndroidApkAdapter mAdapter;
    @Override
    protected void init() {
        super.init();
        mPresenter.getInstalledApps();

    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {

        mAdapter = new AndroidApkAdapter(AndroidApkAdapter.FLAG_APP);

        return mAdapter;
    }

    @Override
    protected void onEmptyViewClick() {

    }

    @Override
    public void showApps(List<AndroidApk> androidApks) {
//        super.showApps(androidApks);
        mAdapter.addData(androidApks);
    }
}
