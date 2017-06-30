package com.xiaoqianghe.wqplay.http;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.IndexBean;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @包名: com.xiaoqianghe.wqplay.http
 * @类名: ApiService
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/3/27 17:54
 * @描述 : TODO
 */

public interface ApiService {


    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

//        @GET("featured")
//        Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParams);


    @GET("featured2")
    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

    @GET("toplist")
    public  Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page);

    @GET("game")
    public  Observable<BaseBean<PageBean<AppInfo>>> games(@Query("page") int page);

    @GET("index")
    public  Observable<BaseBean<IndexBean>> index();


}
