package com.xiaoqianghe.wqplay.ui.Fragment;

import com.xiaoqianghe.wqplay.bean.Subject;
import com.xiaoqianghe.wqplay.bean.SubjectDetail;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.di.component.DaggerSubjectComponent;
import com.xiaoqianghe.wqplay.di.module.SubjectModule;
import com.xiaoqianghe.wqplay.presenter.SubjectPresenter;
import com.xiaoqianghe.wqplay.presenter.contract.SubjectContract;

/**
 * Author：Wq
 * Date：2017/12/12 11:01
 * Description：//todo
 */

public abstract class BaseSubjectFragment extends ProgressDialogFragment<SubjectPresenter> implements SubjectContract.SubjectView{


    @Override
    public void showSubject(PageBean<Subject> subjects) {

    }

    @Override
    public void onLoadMoreComplete() {

    }

    @Override
    public void showSubjectDetail(SubjectDetail subjectDetail) {

    }

    @Override
    protected void onEmptyViewClick() {

    }





    @Override
    public void setupActivityComponent(AppComponent appComponent) {

        DaggerSubjectComponent.builder().appComponent(appComponent).subjectModule(new SubjectModule(this))
                .build().inject(this);


    }
}
