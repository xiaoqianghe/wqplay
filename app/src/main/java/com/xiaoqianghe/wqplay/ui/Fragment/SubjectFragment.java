package com.xiaoqianghe.wqplay.ui.Fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.Subject;
import com.xiaoqianghe.wqplay.bean.SubjectDetail;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.ui.adapter.SubjectAdapter;
import com.xiaoqianghe.wqplay.ui.widget.SpaceItemDecoration2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：Wq
 * Date：2017/12/12 11:17
 * Description：//todo
 */

public class SubjectFragment extends BaseSubjectFragment implements  BaseQuickAdapter.RequestLoadMoreListener{


    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    Unbinder unbinder;


    int page = 0;

    private SubjectAdapter mSubjectAdapter;




    @Override
    public void showSubject(PageBean<Subject> subjects) {
        super.showSubject(subjects);
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        super.setupActivityComponent(appComponent);
    }

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void init() {


        initRecycleView();

        mPresenter.getSubjects(page);


    }

    private void initRecycleView() {

        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),2);

        recycleView.setLayoutManager(layoutManager);
        SpaceItemDecoration2 dividerDec=new SpaceItemDecoration2(5);

        recycleView.addItemDecoration(dividerDec);

        mSubjectAdapter=new SubjectAdapter();

        mSubjectAdapter.setOnLoadMoreListener(this);

        recycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Subject subject=mSubjectAdapter.getItem(position);
            }
        });



    }

    @Override
    public void showSubjectDetail(SubjectDetail subjectDetail) {
        super.showSubjectDetail(subjectDetail);
    }

    @Override
    public void onLoadMoreComplete() {
        super.onLoadMoreComplete();
    }



    @Override
    public void onLoadMoreRequested() {

        mPresenter.getSubjects(page);

    }
}
