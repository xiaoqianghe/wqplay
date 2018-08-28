package com.xiaoqianghe.wqplay.presenter.contract;

import com.xiaoqianghe.wqplay.bean.requestbean.IndexBean;
import com.xiaoqianghe.wqplay.ui.BaseView;
import com.xiaoqianghe.wqplay.ui.DBaseView;

/**
 * Author：Wq
 * Date：2018/8/16 11:34
 * Description：//todo
 */

public interface DTopListActivityContract {

    public  interface DTopListActivityView extends BaseView {

        void showResultData(IndexBean indexBean);
    }


//    public interface DTopListAvtivityModel {
//
//        Observable<BaseBean<PageBean<AppInfo>>> getTopList(int page);
//
//    }
}
