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
import com.xiaoqianghe.wqplay.bean.requestbean.Category;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
//import com.xiaoqianghe.wqplay.di.component.DaggerCategoryComponent;
//import com.xiaoqianghe.wqplay.di.module.CategoryModule;
import com.xiaoqianghe.wqplay.di.component.DaggerAppDetailComponent;
import com.xiaoqianghe.wqplay.di.component.DaggerCategoryComponent;
import com.xiaoqianghe.wqplay.di.module.CategoryModule;
import com.xiaoqianghe.wqplay.presenter.CategoryPrestenter;
import com.xiaoqianghe.wqplay.presenter.contract.CategoryContract;
import com.xiaoqianghe.wqplay.ui.activity.CategoryAppActivity;
import com.xiaoqianghe.wqplay.ui.adapter.CategoryAdapter;
import com.xiaoqianghe.wqplay.ui.decoration.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：Wq
 * Date：2017/6/9 16:49
 * Description：//todo
 */

public class CategoryFragment extends ProgressDialogFragment<CategoryPrestenter> implements CategoryContract.CategoryView {
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private CategoryAdapter mAdapter;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//       // return super.onCreateView(R.layout.fragment_category, container, savedInstanceState);
//        View view = inflater.inflate(R.layout.fragment_category,container,false);
//        return view;
//    }

    @Override
    protected void onEmptyViewClick() {

    }

    @Override
    protected void init() {

        initRecyclerView();

        mPresenter.getAllCategory();

    }

    private void initRecyclerView() {

        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()) );

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);

        recycleView.addItemDecoration(itemDecoration);

        mAdapter=new CategoryAdapter();
        recycleView.setAdapter(mAdapter);

        recycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), CategoryAppActivity.class);

                intent.putExtra(Constant.CATEGORY,mAdapter.getData().get(position));

                startActivity(intent);
            }
        });




    }

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

//
//        DaggerCategoryComponent.builder().appComponent(appComponent).categoryModule(new CategoryModule(this));
//
//
//
//


        DaggerCategoryComponent.builder().appComponent(appComponent).categoryModule(new CategoryModule(this))
        .build().inject(this);

    }

    @Override
    public void showData(List<Category> categories) {

        mAdapter.addData(categories);

    }



}
