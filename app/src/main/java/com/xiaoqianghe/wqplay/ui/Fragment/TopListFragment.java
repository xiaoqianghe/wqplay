package com.xiaoqianghe.wqplay.ui.Fragment;

import com.xiaoqianghe.wqplay.di.component.AppComponent;


import com.xiaoqianghe.wqplay.di.component.DaggerAppInfoComponent;
import com.xiaoqianghe.wqplay.di.module.AppInfoModule;
import com.xiaoqianghe.wqplay.presenter.AppInfoPresenter;
import com.xiaoqianghe.wqplay.ui.adapter.AppInfoAdapter;

/**
 * Author：Wq
 * Date：2017/6/30 16:17
 * Description：//todo
 */

public class TopListFragment extends BaseAppInfoFragment {
    @Override
    AppInfoAdapter buildAdapter() {
        return  AppInfoAdapter.builder().showPosition(true).showBrief(false).showCategoryName(true).build();
    }

    @Override
    protected int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
    //DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this)).build().inject(this);
     //   DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this)).build().inject(this);

      //  DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this)).build().inject(this);

    }
}
