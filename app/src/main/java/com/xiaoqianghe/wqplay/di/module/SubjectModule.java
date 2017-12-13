package com.xiaoqianghe.wqplay.di.module;

import com.xiaoqianghe.wqplay.data.SubjectModel;
import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.SubjectContract;

import dagger.Module;
import dagger.Provides;

/**
 * Author：Wq
 * Date：2017/12/13 15:19
 * Description：//todo
 */

@Module
public class SubjectModule {

    private SubjectContract.SubjectView mView;

    public SubjectModule(SubjectContract.SubjectView mView) {
        this.mView = mView;
    }



    @FragmentScope
    @Provides
    public SubjectContract.ISubjectModel provideModel(ApiService apiService){

        return   new SubjectModel(apiService);

    }



    @FragmentScope
    @Provides
    public SubjectContract.SubjectView provideView(){

        return mView;
    }



}
