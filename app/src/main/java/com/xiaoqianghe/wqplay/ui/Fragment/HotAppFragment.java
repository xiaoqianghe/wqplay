package com.xiaoqianghe.wqplay.ui.Fragment;

import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.presenter.AppInfoPresenter;
import com.xiaoqianghe.wqplay.ui.adapter.AppInfoAdapter;

/**
 * Author：Wq
 * Date：2017/12/12 17:35
 * Description：//todo
 */

public class HotAppFragment extends BaseAppInfoFragment{

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(true).showBrief(false).showCategoryName(true).rxDownload(mRxdownload).build();
    }

    @Override
    protected int type() {
        return AppInfoPresenter.HOT_APP_LIST;
    }



}
