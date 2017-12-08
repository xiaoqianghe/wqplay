package com.xiaoqianghe.wqplay.di.module;

import com.xiaoqianghe.wqplay.data.SearchModel;
import com.xiaoqianghe.wqplay.di.FragmentScope;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.SearchContract;

import dagger.Module;
import dagger.Provides;
import zlc.season.rxdownload2.RxDownload;

/**
 * Author：Wq
 * Date：2017/12/5 16:34
 * Description：//todo
 */
@Module
public class SearchModule {

    private SearchContract.SearchView mView;

    public SearchModule(SearchContract.SearchView mView) {
        this.mView = mView;
    }



    @FragmentScope
    @Provides
    public SearchContract.ISearchModel provideModel(ApiService apiService){

        return  new SearchModel(apiService);


    }

    @FragmentScope
    @Provides
    public SearchContract.SearchView provideView(){
        return mView;
    }



}
