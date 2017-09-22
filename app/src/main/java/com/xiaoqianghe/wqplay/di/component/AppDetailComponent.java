package com.xiaoqianghe.wqplay.di.component;

import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.di.module.AppDetailModule;
import com.xiaoqianghe.wqplay.ui.Fragment.AppDetailFragment;

import dagger.Component;

/**
 * Author：Wq
 * Date：2017/9/20 16:42
 * Description：//todo
 */

@FragmentScope
@Component(modules = AppDetailModule.class,dependencies = AppComponent.class)
public interface AppDetailComponent {

    void inject(AppDetailFragment fragment);
}
