package com.xiaoqianghe.wqplay.presenter.contract;

import com.xiaoqianghe.wqplay.ui.BaseView;

/**
 * @包名: com.xiaoqianghe.wqplay.presenter.contract
 * @类名: AppInfoContract
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/3/27 19:32
 * @描述 : TODO
 */

public interface AppInfoContract {



    interface AppInfoView extends BaseView{

        void showResultData();

        void onLoadMoreComplete();

    }


}
