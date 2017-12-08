package com.xiaoqianghe.wqplay.di.component;

import android.app.Application;

import com.xiaoqianghe.wqplay.AppApplication;
import com.xiaoqianghe.wqplay.common.DownloadModule;
import com.xiaoqianghe.wqplay.common.rx.RxErrHandler;
import com.xiaoqianghe.wqplay.di.module.AppModule;
import com.xiaoqianghe.wqplay.di.module.HttpModule;
import com.xiaoqianghe.wqplay.http.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import zlc.season.rxdownload2.RxDownload;

/**
 * Author：Wq
 * Date：2017/6/15 11:54
 * Description：//todo
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class, DownloadModule.class})
public interface AppComponent {


    public ApiService getApiService();
    public Application getApplication();
//
//    public RxErrHandler getRxErrHandler();

    public RxDownload getRxDownload();


}
