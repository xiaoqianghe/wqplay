package com.xiaoqianghe.wqplay.data;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.IndexBean;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.ui.activity.BaseActivity;

import retrofit2.Callback;


import io.reactivex.Observable;

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


    
    /**
     * @// TODO: 2017/6/29  indexBean  
     * 
     * */
    public Observable<BaseBean<IndexBean>> index(){

        return  mApiService.index();
    }


    /**
     * 
     * @// TODO: 2017/6/29  顶部列表 
     * 
     * */
    public Observable<BaseBean<PageBean<AppInfo>>> topList(int page){

        return  mApiService.topList(page);
    }



    /**
     * 
     * @// TODO: 2017/6/29 游戏列表数据 
     * 
     * */
    public  Observable<BaseBean<PageBean<AppInfo>>> games(int page){

        return  mApiService.games(page);
    }



    public Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(int categoryid,int page){
        return  mApiService.getFeaturedAppsByCategory(categoryid,page);
    }



    public Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory( int categoryid, int page){

        return  mApiService.getTopListAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory( int categoryid, int page){

        return  mApiService.getNewListAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<AppInfo>> getAppDetail( int id){

        return  mApiService.getAppDetail(id);
    }


}
