package com.xiaoqianghe.wqplay.presenter.contract;

import com.xiaoqianghe.wqplay.bean.Subject;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.IndexBean;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.ui.BaseView;

import io.reactivex.Observable;

/**
 * Author：Wq
 * Date：2018/8/16 11:34
 * Description：//todo
 */

public interface TopListActivityContract {

    public  interface ITestListActivityView extends BaseView {

        void showResultData(IndexBean indexBean);
    }


//    public interface ITopListAvtivityModel {
//
//        Observable<BaseBean<PageBean<AppInfo>>> getTopList(int page);
//
//    }
}
