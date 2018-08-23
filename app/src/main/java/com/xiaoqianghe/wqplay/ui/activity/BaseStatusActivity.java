package com.xiaoqianghe.wqplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.xiaoqianghe.wqplay.ActivityManager;
import com.xiaoqianghe.wqplay.AppApplication;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.presenter.BasePresenter;
import com.xiaoqianghe.wqplay.ui.BaseView;
import com.xiaoqianghe.wqplay.ui.DBaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：Wq
 * Date：2018/8/15 17:46
 * Description：//todo   有 几种状态的Activity 的基类
 */

public abstract class BaseStatusActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    private final String TAG = this.getClass().getSimpleName();


    //    private T mPresenter;
    protected AppApplication mApplication;
    @Inject
    T mPresenter ;

    private Unbinder mUnbinder;

//    private FrameLayout mRootView;
private LinearLayout mRootView;
    private View mViewProgress;
    private FrameLayout mViewContent;
    private View mViewEmpty;
    private TextView mTextError;

    private Toolbar tool_bar;
    private FrameLayout fl_rootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_basestatus);
//        setContentView(setLayout());
        initBaseView();
        setRealContentView();
//        mUnbinder= ButterKnife.bind(this);
        this.mApplication = (AppApplication) getApplication();
        setupAcitivtyComponent(mApplication.getAppComponent());
        init();

        ActivityManager.getInstance().addActivity(this);
    }


    public void initBaseView() {
        mRootView =(LinearLayout)findViewById(R.id.rootView);
//        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress,container,false);
        mViewProgress=mRootView.findViewById(R.id.view_progress);
        mViewContent=(FrameLayout)mRootView.findViewById(R.id.view_content);
        mViewEmpty=mRootView.findViewById(R.id.view_empty);
        mTextError = (TextView) mRootView.findViewById(R.id.text_tip);

        tool_bar= (Toolbar) mRootView.findViewById(R.id.tool_bar);
        fl_rootView= (FrameLayout)mRootView.findViewById(R.id.fl_rootView);
        mViewEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });

    }



    private Toolbar getToolBar() {
        return (Toolbar) findViewById(R.id.tool_bar);
    }

    private void setRealContentView() {
        View realContentView= LayoutInflater.from(this).inflate(setLayout(),mViewContent,true);
        mUnbinder=ButterKnife.bind(this, realContentView);
    }


//
//        @Override
//    public void setContentView(int layoutResID) {
//            View view = getLayoutInflater().inflate(setLayout(), null);
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//            if (null != mRootView) {
//                mRootView.addView(view, lp);
//            }
//        }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder!=Unbinder.EMPTY){
            mUnbinder.unbind();
        }

        ActivityManager.getInstance().removeActivity(this);
    }


    protected abstract void init();//初始化

    protected abstract int setLayout();//设置布局文件Id

    public abstract  void setupAcitivtyComponent(AppComponent appComponent);


    @Override
    public void showLoading() {

        showView(R.id.view_progress);

    }

    @Override
    public void dismissLoading() {
        showView(R.id.view_content);

    }

    @Override
    public void showError(String str) {
        showEmptyView(str);
    }




    protected abstract void onEmptyViewClick();






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




    //
//    public void showView(int viewId){
//        for(int i=0;i<mRootView.getChildCount();i++){
//            if( mRootView.getChildAt(i).getId() == viewId||tool_bar.getId()==viewId){
//                mRootView.getChildAt(i).setVisibility(View.VISIBLE);
////                if(mRootView.getChildAt(i).getId()==R.id.view_empty){
////                    Log.d(TAG,"======哈哈哈mRootView.getChildAt(i)==emptyView");
////                    if(mRootView.getChildAt(i).getVisibility()==View.VISIBLE){
////                        Log.d(TAG,"======mRootView.getChildAt(i).getVisibility()  showEmptyView::emptyView显示");
////                        mTextError.setVisibility(View.VISIBLE);
////                        mTextError.setText("数据异常啦啦啦");
////                    }
////                }
//            }
//            else {
//                mRootView.getChildAt(i).setVisibility(View.GONE);
//            }
//        }
//    }




//=================================
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     *携带数据的页面跳转
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }



}
