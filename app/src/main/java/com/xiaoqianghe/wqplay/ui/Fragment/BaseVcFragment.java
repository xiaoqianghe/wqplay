package com.xiaoqianghe.wqplay.ui.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoqianghe.wqplay.AppApplication;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.presenter.BasePresenter;
import com.xiaoqianghe.wqplay.ui.BaseView;
import com.xiaoqianghe.wqplay.ui.DBaseView;
import com.xiaoqianghe.wqplay.ui.activity.BaseVcActivity;

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

public abstract class BaseVcFragment extends IBaseFragment implements BaseView {
    private final String TAG = this.getClass().getSimpleName();


    private BaseVcActivity mActivity;
    private ProgressDialog mProgressDialog;
    private Unbinder mUnbinder;
    protected AppApplication mApplication;
//    private FrameLayout mRootView;
    private LinearLayout mRootView;
    private View mViewProgress;
    private FrameLayout mViewContent;
    private View mViewEmpty;
    private TextView mTextError;
    private FrameLayout fl_rootView;








    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // super.onCreateView(inflater, container, savedInstanceState);
        mRootView = ( LinearLayout) inflater.inflate(R.layout.fragment_basestatus_mvc,container,false);
        mViewProgress=mRootView.findViewById(R.id.view_progress);
        mViewContent=(FrameLayout)mRootView.findViewById(R.id.view_content);
        mViewEmpty=mRootView.findViewById(R.id.view_empty);
        mTextError = (TextView) mRootView.findViewById(R.id.text_tip);

        fl_rootView= (FrameLayout) mRootView.findViewById(R.id.fl_rootView);

        mViewEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });


//        mRootView=inflater.inflate(setLayout(),container,false);
//        mUnbinder=ButterKnife.bind(this,mRootView);
        return  mRootView;
    }

    protected abstract void onEmptyViewClick();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication=(AppApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        setRealContentView();
        init();

    }


    private void setRealContentView() {
        View realContentView=LayoutInflater.from(getActivity()).inflate(setLayout(),mViewContent,true);
        mUnbinder=ButterKnife.bind(this, realContentView);
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
        showProgressView();

    }

    @Override
    public void dismissLoading() {
        showContentView();
    }

    @Override
    public void showError(String str) {
        showEmptyView(str);
    }


    public void showView(int viewId){
        for(int i=0;i<fl_rootView.getChildCount();i++){
            if( fl_rootView.getChildAt(i).getId() == viewId){
                fl_rootView.getChildAt(i).setVisibility(View.VISIBLE);
            }
            else {
                fl_rootView.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }




    public void  showProgressView(){
        showView(R.id.view_progress);
    }

    public void showContentView(){
        showView(R.id.view_content);
    }

    public void showEmptyView(){
        showView(R.id.view_empty);
    }


    public void showEmptyView(int resId){
        showEmptyView();
        mTextError.setText(resId);
    }

    public void showEmptyView(String msg){
        Log.d(TAG,"======showEmptyView::"+msg);
        showEmptyView();
        if(null!=msg){
            mTextError.setText(msg);
        }

    }



}
