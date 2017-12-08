package com.xiaoqianghe.wqplay.di.module;

import android.app.Application;

import com.xiaoqianghe.wqplay.data.AppManagerModel;
import com.xiaoqianghe.wqplay.presenter.contract.AppManagerContract;

import dagger.Module;
import dagger.Provides;
import zlc.season.rxdownload2.RxDownload;

/**
 * Author：Wq
 * Date：2017/11/30 17:46
 * Description：//todo
 */
@Module()
public class AppManagerModule {

    private AppManagerContract.AppManagerView mView;

    public AppManagerModule(AppManagerContract.AppManagerView view){
        this.mView=view;
    }



    @Provides
    public AppManagerContract.AppManagerView provideView(){

        return mView;
    }



    @Provides
    public AppManagerContract.IAppManagerModel provideModel(Application application,RxDownload rxDownload){

        return new AppManagerModel(application,rxDownload);



    }

}
