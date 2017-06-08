package com.xiaoqianghe.wqplay.presenter.contract;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.presenter.BasePresenter;
import com.xiaoqianghe.wqplay.ui.BaseView;

import java.util.List;

/**
 * Author：Wq
 * Date：2017/6/1 16:11
 * Description：//todo
 */

public interface RecommendContract {

    interface View extends BaseView{

        void showError(String str);
        void showNodata();
        void showResult(List<AppInfo> datas);
    }




}
