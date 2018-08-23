package com.xiaoqianghe.wqplay.di.component;

import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.di.module.DTopListActivityModule;
import com.xiaoqianghe.wqplay.ui.activity.DTopListActivity;
import com.xiaoqianghe.wqplay.ui.activity.TopListActivity;

import dagger.Component;

/**
 * Author：Wq
 * Date：2018/8/16 15:11
 * Description：//todo
 */

@FragmentScope
@Component(modules = DTopListActivityModule.class,dependencies = AppComponent.class)
public interface DTopListActivityComponent {

    void inject(DTopListActivity avtivity);
}
