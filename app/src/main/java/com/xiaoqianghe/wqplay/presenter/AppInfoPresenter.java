package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.data.AppInfoModel;
import com.xiaoqianghe.wqplay.presenter.contract.AppInfoContract;

/**
 * @包名: com.xiaoqianghe.wqplay.presenter
 * @类名: AppInfoPresenter
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/4/5 11:16
 * @描述 : TODO
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel,AppInfoContract.AppInfoView> {


    public static final int  TOP_LIST=1;
    public static final int  GAME=2;

    public AppInfoPresenter(AppInfoModel mModel, AppInfoContract.AppInfoView mView) {
        super(mModel, mView);
    }

    public void requestData(int type,int page){

    }
}
