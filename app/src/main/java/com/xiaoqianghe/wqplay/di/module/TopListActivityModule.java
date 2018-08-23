package com.xiaoqianghe.wqplay.di.module;

import com.xiaoqianghe.wqplay.data.TopListActivityModel;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.TopListActivityContract;

import dagger.Module;
import dagger.Provides;

/**
 * Author：Wq
 * Date：2018/8/16 15:14
 * Description：//todo
 */
@Module
public class TopListActivityModule {

    private TopListActivityContract.ITestListActivityView  mView;

    public TopListActivityModule(TopListActivityContract.ITestListActivityView mView) {
        this.mView = mView;
    }


    @Provides
    public TopListActivityContract.ITestListActivityView provideView(){
        return mView;
    }

    @Provides
    public TopListActivityModel provideModel(ApiService mApiService){
        return new TopListActivityModel(mApiService);
    }
}
