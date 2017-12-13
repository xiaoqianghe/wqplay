package com.xiaoqianghe.wqplay.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.di.component.DaggerAppInfoComponent;
import com.xiaoqianghe.wqplay.di.module.AppInfoModule;
import com.xiaoqianghe.wqplay.presenter.AppInfoPresenter;
import com.xiaoqianghe.wqplay.presenter.contract.AppInfoContract;
import com.xiaoqianghe.wqplay.ui.activity.AppDetailActivity;
import com.xiaoqianghe.wqplay.ui.adapter.AppInfoAdapter;
import com.xiaoqianghe.wqplay.ui.decoration.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zlc.season.rxdownload2.RxDownload;

/**
 * Author：Wq
 * Date：2017/6/29 14:35
 * Description：//todo
 */

public abstract class BaseAppInfoFragment extends ProgressDialogFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView,BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    Unbinder unbinder;


    @Inject
    RxDownload mRxdownload;

    private AppInfoAdapter mAdapter;
    int page =0;

    @Override
    public void showResultData(PageBean<AppInfo> pageBean) {
        mAdapter.addData(pageBean.getDatas());
        if(pageBean.isHasMore()){
            page++;
        }
        mAdapter.setEnableLoadMore(pageBean.isHasMore());

    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
    }

    @Override
    protected void onEmptyViewClick() {

    }

    @Override
    protected void init() {
        int type=type();
        mPresenter.requestData(type,page);
        initRecycleView();

    }

    private void initRecycleView() {
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()) );

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);

        recycleView.addItemDecoration(itemDecoration);
        mAdapter = buildAdapter();

        mAdapter.setOnLoadMoreListener(this);

        recycleView.setAdapter(mAdapter);

        recycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                AppInfo appInfo = mAdapter.getItem(position);
                mApplication.setView(view);
                Intent intent  = new Intent(getActivity(), AppDetailActivity.class);
                intent.putExtra("appinfo",appInfo);
                startActivity(intent);

            }
        });
    }

    abstract AppInfoAdapter buildAdapter();

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().injectTopListFragment(this);

    }

    @Override
    public void onLoadMoreRequested() {

        mPresenter.requestData(type(),page);

    }

    protected abstract int type();
}
