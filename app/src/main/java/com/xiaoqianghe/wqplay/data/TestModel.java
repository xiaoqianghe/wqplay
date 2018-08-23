package com.xiaoqianghe.wqplay.data;

import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.LoginBean;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.TestContract;

import io.reactivex.Observable;

/**
 * Author：Wq
 * Date：2018/7/18 10:27
 * Description：//todo
 */

public class TestModel implements TestContract.ITestModel {

    private ApiService mApiService;

    public TestModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone, String pwd) {
        return null;
    }
}
