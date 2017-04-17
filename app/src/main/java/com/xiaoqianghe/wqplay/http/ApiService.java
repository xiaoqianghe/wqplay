package com.xiaoqianghe.wqplay.http;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @包名: com.xiaoqianghe.wqplay.http
 * @类名: ApiService
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/3/27 17:54
 * @描述 : TODO
 */

public interface ApiService {
    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

        @GET("featured")
        Call<AppInfo> getAppInfo();


}
