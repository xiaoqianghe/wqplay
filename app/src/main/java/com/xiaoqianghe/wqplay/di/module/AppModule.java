package com.xiaoqianghe.wqplay.di.module;

import com.google.gson.Gson;
import com.xiaoqianghe.wqplay.AppApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author：Wq
 * Date：2017/6/15 10:53
 * Description：//todo
 */
@Module
public class AppModule {

    private AppApplication mAppApplication;

    public AppModule(AppApplication mAppApplication) {
        this.mAppApplication = mAppApplication;
    }

    @Singleton
    @Provides
    public AppApplication provideApplication(){

        return mAppApplication;
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }
}
