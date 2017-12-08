package com.xiaoqianghe.wqplay.data;

import com.xiaoqianghe.wqplay.bean.SearchResult;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.SearchContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author：Wq
 * Date：2017/12/5 16:39
 * Description：//todo
 */

public class SearchModel implements SearchContract.ISearchModel {

    private ApiService mApiService;


    public SearchModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseBean<List<String>>> getSuggestion(String keyword) {
        return mApiService.searchSuggest(keyword);
    }

    @Override
    public Observable<BaseBean<SearchResult>> search(String keyword) {
        return mApiService.search(keyword);
    }
}
