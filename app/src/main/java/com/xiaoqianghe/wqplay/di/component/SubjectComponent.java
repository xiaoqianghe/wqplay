package com.xiaoqianghe.wqplay.di.component;

import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.di.module.SubjectModule;
import com.xiaoqianghe.wqplay.ui.Fragment.BaseSubjectFragment;

import dagger.Component;

/**
 * Author：Wq
 * Date：2017/12/13 15:14
 * Description：//todo
 */
@FragmentScope
@Component(modules = SubjectModule.class,dependencies = AppComponent.class)
public interface SubjectComponent {

    void inject(BaseSubjectFragment fragment);
}
