package com.xiaoqianghe.wqplay.presenter.contract;

import com.xiaoqianghe.wqplay.presenter.BasePresenter;
import com.xiaoqianghe.wqplay.ui.BaseView;

/**
 * Author：Wq
 * Date：2017/6/1 16:11
 * Description：//todo
 */

public interface RecommendContract {

    interface View extends BaseView{

    }


    interface  Presenter extends BasePresenter{

        public abstract void requestData();

    }


}
