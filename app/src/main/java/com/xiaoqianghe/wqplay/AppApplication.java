package com.xiaoqianghe.wqplay;

import android.app.Application;
import android.content.Context;
import android.view.View;


import com.mikepenz.iconics.Iconics;
import com.xiaoqianghe.wqplay.common.font.WqplayFont;
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

    private View mView;

    @Override
    public void onCreate() {
        super.onCreate();
//        mAppComponent= DaggerAppComponent.builder().appModule(new AppModule(this)).httpModule(new HttpModule()).build();
//        mAppComponent= DaggerAppComponent.builder().appModule(new AppModule(this)).httpModule(new HttpModule()).build();

//        mAppComponent=DaggerAppComponent.builder().appModule(new AppModule(this)).httpModule(new HttpModule()).build();

       Iconics.init(getApplicationContext());
        Iconics.registerFont(new WqplayFont());

        mAppComponent= DaggerAppComponent.builder().appModule(new AppModule(this)).httpModule(new HttpModule()).build();



    }


    public static AppApplication get(Context mContext){

        return (AppApplication)mContext.getApplicationContext();

    }

    public AppComponent getAppComponent(){

        return mAppComponent;
    }



    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }
}
