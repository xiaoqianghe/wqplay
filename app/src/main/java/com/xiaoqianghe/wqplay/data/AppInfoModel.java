package com.xiaoqianghe.wqplay.data;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.http.ApiService;

import retrofit2.Callback;

/**
 * @包名: com.xiaoqianghe.wqplay.data
 * @类名: AppInfoModel
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/4/5 11:18
 * @描述 : TODO
 */

public class AppInfoModel {

    private ApiService mApiService;


    public AppInfoModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }


    /**
     * @todo: 获取appInfo信息的业务逻辑
     *
     * */
    private void getAppInfo(Callback<AppInfo> callback){

       // mApiService.getAppInfo().enqueue(callback);

    }
}
