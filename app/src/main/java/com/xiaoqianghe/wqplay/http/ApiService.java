package com.xiaoqianghe.wqplay.http;

import com.xiaoqianghe.wqplay.bean.SearchResult;
import com.xiaoqianghe.wqplay.bean.Subject;
import com.xiaoqianghe.wqplay.bean.SubjectDetail;
import com.xiaoqianghe.wqplay.bean.UpLoadRsBean;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.Category;
import com.xiaoqianghe.wqplay.bean.requestbean.IndexBean;
import com.xiaoqianghe.wqplay.bean.requestbean.LoginBean;
import com.xiaoqianghe.wqplay.bean.requestbean.LoginRequestBean;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
//import rx.Observable;


import io.reactivex.Observable;

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
    Observable<BaseBean<IndexBean>> index();

    @POST("login")
    Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean param);

    @GET("category")
    Observable<BaseBean<List<Category>>> getCategories();




    @GET("category/featured/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);

    @GET("category/toplist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);

    @GET("category/newlist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);


    @GET("app/{id}")
    Observable<BaseBean<AppInfo>> getAppDetail(@Path("id") int id);





    @GET("search/suggest")
    Observable<BaseBean<List<String>>> searchSuggest(@Query("keyword") String keyword);


    @GET("search")
    Observable<BaseBean<SearchResult>> search(@Query("keyword") String keyword);


    @GET("apps/updateinfo")
    Observable<BaseBean<List<AppInfo>>> getAppsUpdateinfo(@Query("packageName") String packageName,@Query("versionCode") String versionCode);



    @GET("subject/hot")
    Observable<BaseBean<PageBean<Subject>>> subjects(@Query("page") int page);

    @GET("subject/{id}")
    Observable<BaseBean<SubjectDetail>> subjectDetail(@Path("id") int id);





    //自定义的上传图片的 自定义接口
    @POST("subject/{id}")
    Observable<BaseBean<UpLoadRsBean>> upLoadData(@Part MultipartBody.Part file);








}
