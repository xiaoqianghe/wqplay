package com.xiaoqianghe.wqplay.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.Subject;
import com.xiaoqianghe.wqplay.common.rx.RxBus;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.ui.Fragment.SubjectDetailFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.SubjectFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Author：Wq
 * Date：2017/12/12 10:39
 * Description：//todo
 */

public class SubjectActivity extends BaseActivity {

    private static final int FRAGMENT_SUBJECT = 0;
    private static final int FRAGMENT_DETAIL = 1;

    private int fragmentIndex=FRAGMENT_SUBJECT;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.activity_subject)
    LinearLayout activitySubject;


    private FragmentManager mFragmentManager;


    private SubjectFragment  mSubjectFragment;
    private SubjectDetailFragment mSubjectDetailFragment;



    @Override
    protected void init() {
        toolBar.setNavigationIcon(new IconicsDrawable(this)
        .icon(Ionicons.Icon.ion_ios_arrow_back)
        .sizeDp(16)
        .color(getResources().getColor(R.color.md_white_1000)));

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handNavigation();
            }
        });

        mFragmentManager=getSupportFragmentManager();
        
        showSubjectFragment();

        showSubjectDetailFragmentRxBus();
        


    }

    private void showSubjectDetailFragmentRxBus(){


        RxBus.getDefault().toObservable(Subject.class).subscribe(new Consumer<Subject>() {
            @Override
            public void accept(@NonNull Subject subject) throws Exception {
                showSubjectDetailFragment(subject);
            }
        });

    }

    private void showSubjectDetailFragment(Subject subject) {


        fragmentIndex = FRAGMENT_DETAIL;
        FragmentTransaction ft = mFragmentManager.beginTransaction();

        if(mSubjectDetailFragment != null){
            ft.remove(mSubjectDetailFragment);
        }

        mSubjectDetailFragment= new SubjectDetailFragment(subject);
        ft.add(R.id.content,mSubjectDetailFragment);


        ft.commit();

        toolBar.setTitle(subject.getTitle());
    }

    private void showSubjectFragment() {
        fragmentIndex=FRAGMENT_SUBJECT;
        toolBar.setTitle("主题");
        FragmentTransaction ft= mFragmentManager.beginTransaction();
        hideFragment(ft);
        if(null==mSubjectFragment){
            mSubjectFragment=new SubjectFragment();
            ft.add(R.id.content,mSubjectFragment);
        }else{
            ft.show(mSubjectFragment);
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if(mSubjectFragment!=null){
            ft.hide(mSubjectFragment);
        }
        if(mSubjectDetailFragment!=null){
            ft.hide(mSubjectDetailFragment);
        }
    }

    private void handNavigation() {
        if(fragmentIndex==FRAGMENT_SUBJECT){
            finish();
        }else{
            showSubjectFragment();
        }
    }

    

    @Override
    protected int setLayout() {
        return R.layout.template_toolbar_framelayout;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
