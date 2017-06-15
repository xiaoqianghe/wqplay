package com.xiaoqianghe.wqplay;

import android.app.Application;
import android.content.Context;

import com.xiaoqianghe.wqplay.di.component.AppComponent;

import com.xiaoqianghe.wqplay.di.component.DaggerAppComponent;
import com.xiaoqianghe.wqplay.di.module.AppModule;
import com.xiaoqianghe.wqplay.di.module.HttpModule;


/**
 * Author：Wq
 * Date：2017/6/7 13:52
 * Description：//todo
 */

public class AppApplication extends Application {


    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
//
        mAppComponent= DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(new HttpModule()).build();

    }


    public static AppApplication getAppApplication(Context mContext){

        return (AppApplication)mContext.getApplicationContext();

    }
}
