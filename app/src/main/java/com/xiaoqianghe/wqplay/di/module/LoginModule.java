package com.xiaoqianghe.wqplay.di.module;

import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.LoginBean;
import com.xiaoqianghe.wqplay.data.LoginModel;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

/**
 * Author：Wq
 * Date：2017/8/18 15:35
 * Description：//todo
 */

@Module
public class LoginModule {

    private LoginContract.LoginView mView;

    public LoginModule(LoginContract.LoginView view){
        this.mView=view;

    }


    @Provides
    public LoginContract.LoginView provideView(){
        return mView;
    }


    @Provides
    public LoginContract.ILoginModel privodeMoel(ApiService apiService){
        return new LoginModel(apiService);
    }

}
