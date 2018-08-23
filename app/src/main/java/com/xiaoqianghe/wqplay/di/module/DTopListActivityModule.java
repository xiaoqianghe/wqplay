package com.xiaoqianghe.wqplay.di.module;

import com.xiaoqianghe.wqplay.data.DTopListActivityModel;
import com.xiaoqianghe.wqplay.data.TopListActivityModel;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.DTopListActivityContract;
import com.xiaoqianghe.wqplay.presenter.contract.TopListActivityContract;

import dagger.Module;
import dagger.Provides;

/**
 * Author：Wq
 * Date：2018/8/16 15:14
 * Description：//todo
 */
@Module
public class DTopListActivityModule {

    private DTopListActivityContract.DTopListActivityView   mView;

    public DTopListActivityModule(DTopListActivityContract.DTopListActivityView  mView) {
        this.mView = mView;
    }


    @Provides
    public DTopListActivityContract.DTopListActivityView  provideView(){
        return mView;
    }

    @Provides
    public DTopListActivityModel provideModel(ApiService mApiService){
        return new DTopListActivityModel(mApiService);
    }
}
