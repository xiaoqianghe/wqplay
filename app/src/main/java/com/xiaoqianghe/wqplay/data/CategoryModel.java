package com.xiaoqianghe.wqplay.data;

import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.Category;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.CategoryContract;

import java.util.List;

import rx.Observable;

/**
 * Author：Wq
 * Date：2017/9/18 15:35
 * Description：//todo
 */

public class CategoryModel implements CategoryContract.ICageoryModel {



    private ApiService mApiService;

    public CategoryModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }


    @Override
    public Observable<BaseBean<List<Category>>> getCategories() {
        return mApiService.getCategories();
    }
}
