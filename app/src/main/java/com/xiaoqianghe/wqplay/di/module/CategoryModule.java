package com.xiaoqianghe.wqplay.di.module;

import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.Category;
import com.xiaoqianghe.wqplay.data.CategoryModel;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.CategoryContract;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

/**
 * Author：Wq
 * Date：2017/9/18 15:29
 * Description：//todo
 */

@Module
public class CategoryModule {



    private CategoryContract.CategoryView mView;


    public CategoryModule(CategoryContract.CategoryView mView){

        this.mView=mView;

    }



    @Provides
    public CategoryContract.CategoryView provideView(){
        return mView;
    }

    @Provides
    public CategoryContract.ICageoryModel provideModel(ApiService api){
        return new CategoryModel(api);
    }
}
