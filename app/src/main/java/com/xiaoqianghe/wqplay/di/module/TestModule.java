package com.xiaoqianghe.wqplay.di.module;

import com.xiaoqianghe.wqplay.data.TestModel;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.TestContract;

import dagger.Module;
import dagger.Provides;

/**
 * Author：Wq
 * Date：2018/7/17 17:44
 * Description：//todo
 */

@Module
public class TestModule {

    public TestContract.TestView mView;


    public TestModule(TestContract.TestView mView){

        this.mView=mView;

    }


    @Provides
    public TestContract.TestView provideTestView(){
        return mView;

    }


    @Provides
    public TestContract.ITestModel provideModel(ApiService mApiService){

        return new TestModel(mApiService);
    }
}
