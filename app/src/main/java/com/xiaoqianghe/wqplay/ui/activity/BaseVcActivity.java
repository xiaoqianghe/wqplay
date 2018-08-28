package com.xiaoqianghe.wqplay.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @包名: com.xiaoqianghe.wqplay.ui.activity
 * @类名: BaseActivity
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/3/27 17:12
 * @描述 : TODO  所有普通Activity的页面的基类
 */

public abstract class BaseVcActivity extends IBaseActivity {
    private final String TAG = this.getClass().getSimpleName();
    protected AppApplication mApplication;
    private Unbinder mUnbinder;


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
        super.setContentView(R.layout.activity_basestatus_d);
//        setContentView(setLayout());
        initBaseView();
        setRealContentView();
        mUnbinder= ButterKnife.bind(this);
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

    protected abstract void onEmptyViewClick();


    private Toolbar getToolBar() {
        return (Toolbar) findViewById(R.id.tool_bar);
    }

    private void setRealContentView() {
        View realContentView= LayoutInflater.from(this).inflate(setLayout(),mViewContent,true);
        mUnbinder=ButterKnife.bind(this, realContentView);
    }


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

}
