package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.ui.BaseView;

/**
 * @包名: com.xiaoqianghe.wqplay.presenter
 * @类名: BasePresenter
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/3/27 17:09
 * @描述 : TODO 所有Presenter的基类
 */

public class BasePresenter<M,V extends BaseView> {

    private M mModel;
    private V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }
}
