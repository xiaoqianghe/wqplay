package com.xiaoqianghe.wqplay.di.module;

import android.app.Application;

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

    private Application mApplication;

    public AppModule(AppApplication mAppApplication) {
        this.mApplication = mApplication;
    }

    @Singleton
    @Provides
    public Application provideApplication(){

        return mApplication;
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }
}
