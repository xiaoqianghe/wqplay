package com.xiaoqianghe.wqplay.di.component;

import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.di.module.TestModule;
import com.xiaoqianghe.wqplay.ui.activity.TestActivity;

import dagger.Component;

/**
 * Author：Wq
 * Date：2018/7/17 17:45
 * Description：//todo
 */


@FragmentScope
@Component(modules=TestModule.class, dependencies = AppComponent.class)
public interface TestComponent {
    void inject (TestActivity activity);
}
