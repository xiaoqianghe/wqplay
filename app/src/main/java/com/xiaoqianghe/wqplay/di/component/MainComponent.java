package com.xiaoqianghe.wqplay.di.component;

import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.di.module.MainModule;
import com.xiaoqianghe.wqplay.ui.activity.MainActivity;

import dagger.Component;

/**
 * Author：Wq
 * Date：2017/12/5 17:11
 * Description：//todo
 */

@FragmentScope
@Component(modules = MainModule.class,dependencies =AppComponent.class )
public interface MainComponent  {
    void  inject(MainActivity activity);

}
