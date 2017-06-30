package com.xiaoqianghe.wqplay.ui.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.presenter.AppInfoPresenter;
import com.xiaoqianghe.wqplay.presenter.contract.AppInfoContract;
import com.xiaoqianghe.wqplay.ui.adapter.AppInfoAdapter;
import com.xiaoqianghe.wqplay.ui.decoration.DividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：Wq
 * Date：2017/6/29 14:35
 * Description：//todo
 */

public abstract class BaseAppInfoFragment extends ProgressDialogFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView,BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    Unbinder unbinder;

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
        mPresenter.requestData(type(),page);
        initRecycleView();

    }

    private void initRecycleView() {
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()) );

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);

        recycleView.addItemDecoration(itemDecoration);
        mAdapter = buildAdapter();

        mAdapter.setOnLoadMoreListener(this);

        recycleView.setAdapter(mAdapter);
    }

    abstract AppInfoAdapter buildAdapter();

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void onLoadMoreRequested() {

        mPresenter.requestData(type(),page);

    }

    protected abstract int type();
}
