package com.xiaoqianghe.wqplay.di.module;

import com.xiaoqianghe.wqplay.data.AppInfoModel;
import com.xiaoqianghe.wqplay.http.ApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Author：Wq
 * Date：2017/9/20 16:35
 * Description：//todo
 */
@Module
public class AppModelModule {



    @Provides
    public AppInfoModel privodeModel(ApiService apiService){

        return new AppInfoModel(apiService);

    }
}
