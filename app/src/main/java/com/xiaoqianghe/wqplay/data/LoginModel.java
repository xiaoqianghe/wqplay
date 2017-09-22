package com.xiaoqianghe.wqplay.data;

import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.LoginBean;
import com.xiaoqianghe.wqplay.bean.requestbean.LoginRequestBean;
import com.xiaoqianghe.wqplay.di.module.LoginModule;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.LoginContract;

import rx.Observable;

/**
 * Author：Wq
 * Date：2017/8/18 16:14
 * Description：//todo
 */

public class LoginModel implements LoginContract.ILoginModel {

    private ApiService mApiService;

    public LoginModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone,String pwd) {
        LoginRequestBean param = new LoginRequestBean();
        param.setEmail(phone);
        param.setPassword(pwd);

        return mApiService.login(param);
    }
}
