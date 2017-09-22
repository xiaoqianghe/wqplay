package com.xiaoqianghe.wqplay.di.component;

import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.di.module.CategoryModule;
import com.xiaoqianghe.wqplay.ui.Fragment.CategoryFragment;

import dagger.Component;

/**
 * Author：Wq
 * Date：2017/9/18 15:45
 * Description：//todo
 */


@FragmentScope
@Component(modules = CategoryModule.class,dependencies =AppComponent.class )
public interface CategoryComponent  {

    void inject(CategoryFragment fragment);
}
