package com.xiaoqianghe.wqplay.di.module;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.AppsUpdateBean;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.data.MainModel;
import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.MainContract;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

/**
 * Author：Wq
 * Date：2017/12/5 16:55
 * Description：//todo
 */


@Module
public class MainModule {

    private MainContract.MainView mView;

    public MainModule(MainContract.MainView mView) {
        this.mView = mView;
    }


    @FragmentScope
    @Provides
    public  MainContract.MainView providesView(){

        return mView;

    }



    @FragmentScope
    @Provides
    public MainContract.IMainModel provideModel(ApiService apiService){

        return new MainModel(apiService);

    }



}
