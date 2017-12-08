package com.xiaoqianghe.wqplay.ui.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.common.apkparset.AndroidApk;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.di.component.DaggerAppManagerComponent;
import com.xiaoqianghe.wqplay.di.module.AppManagerModule;
import com.xiaoqianghe.wqplay.presenter.AppManagerPresenter;
import com.xiaoqianghe.wqplay.presenter.contract.AppManagerContract;
import com.xiaoqianghe.wqplay.ui.decoration.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Author：Wq
 * Date：2017/11/30 14:41
 * Description：//todo
 */

public abstract class AppManangerFragment extends ProgressDialogFragment<AppManagerPresenter> implements AppManagerContract.AppManagerView {


    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    @Override
    protected void init() {

        setupRecyclerView();

    }

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {


        DaggerAppManagerComponent.builder()
                .appComponent(appComponent)
                .appManagerModule(new AppManagerModule(this))
                .build().inject(this);

    }


    private void setupRecyclerView() {


        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);

        recycleView.addItemDecoration(itemDecoration);


        recycleView.setAdapter(setupAdapter());
    }


    protected abstract RecyclerView.Adapter setupAdapter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {

    }

    @Override
    public void showApps(List<AndroidApk> androidApks) {

    }

    @Override
    public void showUpdateApps(List<AppInfo> appInfos) {

    }
}
