package com.xiaoqianghe.wqplay.presenter.contract;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.AppsUpdateBean;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.ui.BaseView;

import java.util.List;


import io.reactivex.Observable;

/**
 * Author：Wq
 * Date：2017/12/4 16:39
 * Description：//todo
 */

public class MainContract {

    public interface MainView extends BaseView {

        void requestPermissonSuccess();
        void requestPermissonFail();

        void changeAppNeedUpdateCount(int  count);

    }


    public interface IMainModel{


        Observable<BaseBean<List<AppInfo>>>  getUpdateApps(AppsUpdateBean param);


    }
}
