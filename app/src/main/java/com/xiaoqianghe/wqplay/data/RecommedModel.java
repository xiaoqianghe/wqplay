package com.xiaoqianghe.wqplay.data;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.http.ApiService;

import retrofit2.Callback;

/**
 * Author：Wq
 * Date：2017/6/1 16:31
 * Description：//todo
 */

public class RecommedModel {

    private ApiService mApiServices;

    public RecommedModel(ApiService apiService) {
        this.mApiServices=apiService;
    }

    /**
     *
     *
     * */
    public void getApps(Callback<PageBean<AppInfo>> callback){
        mApiServices.getApps("{'page':0}").enqueue(callback);
    }


}
