package com.xiaoqianghe.wqplay.ui.Fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.Subject;
import com.xiaoqianghe.wqplay.bean.SubjectDetail;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.common.imageloader.ImageLoader;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.ui.adapter.AppInfoAdapter;
import com.xiaoqianghe.wqplay.ui.decoration.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zlc.season.rxdownload2.RxDownload;

/**
 * Author：Wq
 * Date：2017/12/12 11:20
 * Description：//todo
 */

public class SubjectDetailFragment extends BaseSubjectFragment {


    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private Subject subject;

    private AppInfoAdapter adapter;

    @Inject
    RxDownload rxdownload;

    public SubjectDetailFragment(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void showSubject(PageBean<Subject> subjects) {
        super.showSubject(subjects);
    }

    @Override
    public void onLoadMoreComplete() {
        super.onLoadMoreComplete();
    }

    @Override
    public void showSubjectDetail(SubjectDetail subjectDetail) {
        ImageLoader.load(Constant.BASE_IMG_URL+ subjectDetail.getPhoneBigIcon(),imageview);
        txtDesc.setText(subjectDetail.getDescription());
        adapter.addData(subjectDetail.getListApp());
    }

    @Override
    protected void onEmptyViewClick() {
        super.onEmptyViewClick();
    }

    @Override
    protected void init() {

        initRecycleView();

        mPresenter.getgetSubjectDetail(subject.getRelatedId());

    }

    private void initRecycleView() {

        adapter= AppInfoAdapter.builder().showBrief(false).showCategoryName(true)
                .rxDownload(rxdownload).build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);

        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setAdapter(adapter);



    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_subject_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        super.setupActivityComponent(appComponent);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}


