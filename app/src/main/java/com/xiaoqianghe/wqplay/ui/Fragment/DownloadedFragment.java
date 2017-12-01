package com.xiaoqianghe.wqplay.ui.Fragment;

import android.support.v7.widget.RecyclerView;

import com.xiaoqianghe.wqplay.common.apkparset.AndroidApk;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.ui.adapter.AndroidApkAdapter;

import java.util.List;

/**
 * Author：Wq
 * Date：2017/11/30 17:37
 * Description：//todo
 */

public class DownloadedFragment extends AppManangerFragment {



    AndroidApkAdapter mAdapter;



    @Override
    protected RecyclerView.Adapter setupAdapter() {

        mAdapter = new AndroidApkAdapter(AndroidApkAdapter.FLAG_APK);
        return mAdapter;
    }

    @Override
    protected void onEmptyViewClick() {

    }

    @Override
    protected void init() {
        super.init();

        mPresenter.getLocalApks();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
//        super.setupActivityComponent(appComponent);
    }

    @Override
    public void showApps(List<AndroidApk> androidApks) {
//        super.showApps(androidApks);

        mAdapter.addData(androidApks);
    }
}
