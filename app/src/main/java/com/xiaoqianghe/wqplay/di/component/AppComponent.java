package com.xiaoqianghe.wqplay.di.component;

import com.xiaoqianghe.wqplay.di.module.AppModule;
import com.xiaoqianghe.wqplay.di.module.HttpModule;
import com.xiaoqianghe.wqplay.http.ApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author：Wq
 * Date：2017/6/15 11:54
 * Description：//todo
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {


    public ApiService getApiService();


}
