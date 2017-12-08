package com.xiaoqianghe.wqplay.ui.Fragment;

import android.support.v7.widget.RecyclerView;

import com.xiaoqianghe.wqplay.ui.adapter.DownloadingAdapter;

import java.util.List;

import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Author：Wq
 * Date：2017/12/4 10:47
 * Description：//todo
 */

public class DownloadingFragment extends AppManangerFragment {

    private DownloadingAdapter mAdapter;

    @Override
    protected void init() {
        super.init();

        mPresenter.getDownLoadingApps();
    }

    public DownloadingFragment() {


    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {


        mAdapter= new DownloadingAdapter(mPresenter.getRxDowanload());
        return mAdapter;
    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {
//        super.showDownloading(downloadRecords);

        mAdapter.addData(downloadRecords);
    }

    @Override
    protected void onEmptyViewClick() {

    }
}
