package com.xiaoqianghe.wqplay.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.xiaoqianghe.wqplay.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @包名: com.xiaoqianghe.wqplay.ui.activity
 * @类名: BaseActivity
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/3/27 17:12
 * @描述 : TODO  所有Activity的页面的基类
 */

public abstract class BaseActivity<T extends BasePresenter>extends Activity {

    private T mPresenter;

    private Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        mUnbinder= ButterKnife.bind(this);
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
}
