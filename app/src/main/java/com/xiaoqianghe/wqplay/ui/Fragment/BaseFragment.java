package com.xiaoqianghe.wqplay.ui.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoqianghe.wqplay.AppApplication;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.presenter.BasePresenter;
import com.xiaoqianghe.wqplay.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @包名: com.xiaoqianghe.wqplay.ui.Fragment
 * @类名: BaseFragment
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/3/27 17:41
 * @描述 : TODO 所有的Fragment的基类
 */

public abstract class BaseFragment<T extends BasePresenter>extends Fragment implements BaseView {

    private ProgressDialog mProgressDialog;
    private Unbinder mUnbinder;
    private View mRootView;
    private AppApplication mApplication;

    @Inject
    T mPresenter;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // super.onCreateView(inflater, container, savedInstanceState);
        mRootView=inflater.inflate(setLayout(),container,false);
        mUnbinder= ButterKnife.bind(this,mRootView);
        return  mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication=(AppApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        init();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder!=Unbinder.EMPTY){
            mUnbinder.unbind();

        }
    }

    protected abstract void init();//初始化

    protected abstract int setLayout();//设置布局文件Id



    public abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    public void showLoading() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("loading......");

        mProgressDialog.show();

    }

    @Override
    public void dismissLoading() {
        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }


    }
}
