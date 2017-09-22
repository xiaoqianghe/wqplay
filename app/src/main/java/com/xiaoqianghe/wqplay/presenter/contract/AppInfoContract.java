package com.xiaoqianghe.wqplay.presenter.contract;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.IndexBean;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.ui.BaseView;

/**
 * @包名: com.xiaoqianghe.wqplay.presenter.contract
 * @类名: AppInfoContract
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/3/27 19:32
 * @描述 : TODO
 */

public interface AppInfoContract {



    interface View extends BaseView{





        void  showResult(IndexBean indexBean);


        void onRequestPermissonSuccess();
        void onRequestPermissonError();



    }



    interface AppInfoView extends BaseView{

        void showResultData(PageBean<AppInfo> page);

        void onLoadMoreComplete();

    }

    interface  AppDetailView extends BaseView{

        void showAppDetail(AppInfo appInfo);

    }


}
