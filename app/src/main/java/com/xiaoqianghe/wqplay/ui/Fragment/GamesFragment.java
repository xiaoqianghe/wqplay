package com.xiaoqianghe.wqplay.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
//import com.xiaoqianghe.wqplay.di.component.DaggerAppInfoComponent;
import com.xiaoqianghe.wqplay.di.component.DaggerAppInfoComponent;
import com.xiaoqianghe.wqplay.di.module.AppInfoModule;
import com.xiaoqianghe.wqplay.presenter.AppInfoPresenter;
import com.xiaoqianghe.wqplay.ui.adapter.AppInfoAdapter;

/**
 * Author：Wq
 * Date：2017/6/9 16:46
 * Description：//todo
 */

public class GamesFragment extends BaseAppInfoFragment{



    @Override
    public void setupActivityComponent(AppComponent appComponent) {

//        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this)).build().injectGamesFragment(this);

        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this)).build().injectGamesFragment(this);

    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(false).showBrief(true).showCategoryName(true).build();
    }

    @Override
    protected int type() {
        return AppInfoPresenter.GAME;
    }
}
