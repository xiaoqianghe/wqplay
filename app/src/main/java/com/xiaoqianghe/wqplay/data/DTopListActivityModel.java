package com.xiaoqianghe.wqplay.data;

import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.IndexBean;
import com.xiaoqianghe.wqplay.http.ApiService;

import io.reactivex.Observable;

/**
 * Author：Wq
 * Date：2018/8/16 11:56
 * Description：//todo
 */

public class DTopListActivityModel {

    private ApiService mApiService;
    public DTopListActivityModel(ApiService mApiService) {


         this.mApiService=mApiService;

    }

    /**
     * @// TODO: 2017/6/29  indexBean
     *
     * */
    public Observable<BaseBean<IndexBean>> index(){

        return  mApiService.index();
    }
}
