package com.xiaoqianghe.wqplay.di.component;

import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.di.module.SearchModule;
import com.xiaoqianghe.wqplay.ui.activity.SearchActivity;

import dagger.Component;

/**
 * Author：Wq
 * Date：2017/12/5 16:49
 * Description：//todo
 */
@FragmentScope
@Component(modules= SearchModule.class,dependencies = AppComponent.class)
public interface SearchComponent {

    void inject(SearchActivity fragment);
}
