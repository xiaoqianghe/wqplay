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

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication(){

        return mApplication;
    }

    @Provides
    @Singleton
    public Gson provideGson(){
        return new Gson();
    }
}
