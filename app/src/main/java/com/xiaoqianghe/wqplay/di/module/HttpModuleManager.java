package com.xiaoqianghe.wqplay.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.xiaoqianghe.wqplay.AppApplication;
import com.xiaoqianghe.wqplay.BuildConfig;
import com.xiaoqianghe.wqplay.common.http.CommonParamsInterceptor;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.http.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：Wq
 * Date：2017/6/15 11:02
 * Description：//todo   非dagger2 模式下的网络请求的工具类
 */


public class HttpModuleManager {


    public static HttpModuleManager instance;
    public Retrofit retrofit;
    public ApiService apiService;
    public static HttpModuleManager getInstance(){
        if(null==instance){
            synchronized (HttpModuleManager.class){
                if(null==instance){
                    instance=new HttpModuleManager();
                }
            }
        }
        return instance;
    }

    public ApiService getApiService(){
        return apiService;
    }


    public HttpModuleManager(){
        create();
    }

    public void create() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.BASE_URL)
                .client(okHttpClient())
                .build();
        apiService = retrofit.create(ApiService.class);
    }




    public  OkHttpClient okHttpClient(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        return builder
//                .addInterceptor(new CommonParamsInterceptor(application,gson))
                //@TODO 这里需要传入参数
                .addInterceptor(new CommonParamsInterceptor(AppApplication.getContext(),new Gson()))
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

    }






//====================================================================================
    public OkHttpClient provideOkHttpClient(Application application, Gson gson){
        //Application application, Gson gson
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
       return builder
//                .addInterceptor(new CommonParamsInterceptor(application,gson))
               .addInterceptor(new CommonParamsInterceptor(application,gson))
               .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }



    public Retrofit provideRetrofit(OkHttpClient okHttpClient){

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);

        return builder.build();
    }



    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
    //=================================================================
}
