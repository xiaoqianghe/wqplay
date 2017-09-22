package com.xiaoqianghe.wqplay.di.component;

import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.di.module.LoginModule;
import com.xiaoqianghe.wqplay.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Author：Wq
 * Date：2017/8/18 16:34
 * Description：//todo
 */
@FragmentScope
@Component(modules= LoginModule.class ,dependencies=AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);


}
