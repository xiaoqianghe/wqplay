package com.xiaoqianghe.wqplay.di.module;

import com.google.gson.GsonBuilder;
import com.xiaoqianghe.wqplay.http.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：Wq
 * Date：2017/6/15 11:02
 * Description：//todo
 */

@Module
public class HttpModule {




    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){

        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        return new OkHttpClient.Builder()
                // HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
//                .addInterceptor(new HeadInterceptor())
                .addInterceptor(logging)
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)

                .build();



    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient);

        return builder.build();
    }


    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit){

        return retrofit.create(ApiService.class);

    }
}