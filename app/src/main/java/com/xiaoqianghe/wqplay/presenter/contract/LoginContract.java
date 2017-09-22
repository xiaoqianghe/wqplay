package com.xiaoqianghe.wqplay.presenter.contract;

import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.LoginBean;
import com.xiaoqianghe.wqplay.ui.BaseView;

import rx.Observable;

/**
 * Author：Wq
 * Date：2017/8/18 14:38
 * Description：//todo
 */

public class LoginContract {

    //LoginView
    public interface LoginView extends BaseView {

        void checkPhoneError();
        void checkoutPhoneSuccess();
        void loginSuccess(LoginBean mBean);

    }

    //
    public interface ILoginModel{
        Observable<BaseBean<LoginBean>> login(String phone, String pwd);
    }


}
