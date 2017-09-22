package com.xiaoqianghe.wqplay.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xiaoqianghe.wqplay.AppApplication;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @包名: com.xiaoqianghe.wqplay.ui.activity
 * @类名: BaseActivity
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/3/27 17:12
 * @描述 : TODO  所有Activity的页面的基类
 */

public abstract class BaseActivity<T extends BasePresenter>extends AppCompatActivity {

//    private T mPresenter;
protected AppApplication mApplication;
    @Inject
    T mPresenter ;

    private Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        mUnbinder= ButterKnife.bind(this);


        this.mApplication = (AppApplication) getApplication();
        setupAcitivtyComponent(mApplication.getAppComponent());
        init();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder!=Unbinder.EMPTY){
            mUnbinder.unbind();

        }
    }

    protected abstract void init();//初始化

    protected abstract int setLayout();//设置布局文件Id

    public abstract  void setupAcitivtyComponent(AppComponent appComponent);
}
