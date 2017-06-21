package com.xiaoqianghe.wqplay.di.component;

import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.di.module.RecommendModule;
import com.xiaoqianghe.wqplay.ui.Fragment.RecommendFragment;
import com.xiaoqianghe.wqplay.ui.Fragment.RecommendProFragment;

import dagger.Component;


/**
 * Author：Wq
 * Date：2017/6/15 10:45
 * Description：//todo
 */

@FragmentScope
@Component(modules = RecommendModule.class,dependencies =AppComponent.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);

}
