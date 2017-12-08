package com.xiaoqianghe.wqplay.di.component;

import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.di.module.AppDetailModule;
import com.xiaoqianghe.wqplay.di.module.AppManagerModule;
import com.xiaoqianghe.wqplay.ui.Fragment.AppManangerFragment;

import dagger.Component;

/**
 * Author：Wq
 * Date：2017/12/7 16:05
 * Description：//todo
 */

@FragmentScope
@Component(modules = AppManagerModule.class,dependencies = AppComponent.class)
public interface AppManagerComponent {


    void inject(AppManangerFragment fragment);
}
