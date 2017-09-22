package com.xiaoqianghe.wqplay.di.module;

import com.xiaoqianghe.wqplay.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Author：Wq
 * Date：2017/9/20 16:31
 * Description：//todo
 */

@Module(includes = {AppModelModule.class})
public class AppDetailModule {

    private AppInfoContract.AppDetailView mView;


    public AppDetailModule(AppInfoContract.AppDetailView mView){
        this.mView=mView;
    }



    @Provides
    public AppInfoContract.AppDetailView provideView(){

        return mView;
    }
}
