package com.xiaoqianghe.wqplay.di.component;

import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.di.module.AppInfoModule;
import com.xiaoqianghe.wqplay.ui.Fragment.GamesFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.TopListFragment;

import dagger.Component;

/**
 * Author：Wq
 * Date：2017/6/30 16:43
 * Description：//todo
 */
@FragmentScope
@Component(modules= AppInfoModule.class,dependencies = AppComponent.class)
public interface AppInfoComponent {


    void inject(TopListFragment fragment);
    void injectGamesFragment(GamesFragment fragment);

}
