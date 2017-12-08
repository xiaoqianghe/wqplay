package com.xiaoqianghe.wqplay.data;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.AppsUpdateBean;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.MainContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author：Wq
 * Date：2017/12/5 17:04
 * Description：//todo
 */

public class MainModel implements MainContract.IMainModel {

    private ApiService apiService;

    public MainModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<AppInfo>>> getUpdateApps(AppsUpdateBean param) {
        return apiService.getAppsUpdateinfo(param.getPackageName(),param.getVersionCode());
    }
}
