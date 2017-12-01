package com.xiaoqianghe.wqplay.presenter;

import android.util.Log;

import com.hwangjr.rxbus.RxBus;
import com.xiaoqianghe.wqplay.bean.requestbean.LoginBean;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ErrHandlerSubscriber;
import com.xiaoqianghe.wqplay.common.util.ACache;
import com.xiaoqianghe.wqplay.common.util.VerificationUtils;
import com.xiaoqianghe.wqplay.presenter.contract.LoginContract;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Author：Wq
 * Date：2017/8/18 14:47
 * Description：//todo
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel,LoginContract.LoginView> {


    @Inject
    public LoginPresenter(LoginContract.ILoginModel mModel, LoginContract.LoginView mView) {
        super(mModel, mView);
    }

    public void login(String phone,String pwd){
        Log.d("LoginPresenter","phone="+phone);
        Log.d("LoginPresenter","pwd="+pwd);


        //校验输入的登录密码


        if(!VerificationUtils.matcherPhoneNum(phone)){
            mView.checkPhoneError();
        }else{
            mView.checkoutPhoneSuccess();
        }




        mModel.login(phone,pwd).compose(RxHttpResponseCompat.<LoginBean>compatResult())
                .subscribe(new ErrHandlerSubscriber<LoginBean>(mContext) {

//                    @Override
//                    public void onStart() {
//                        //super.onStart();
//
//                        mView.showLoading();
//                    }


                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.dismissLoading();
                    }

                    @Override
                    public void onComplete() {

                        mView.dismissLoading();

                    }

//                    @Override
//                    public void onCompleted() {
//                        mView.dismissLoading();
//
//                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.loginSuccess(loginBean);

                        //
                        saveUser(loginBean);

                        RxBus.get().post(loginBean.getUser());

                    }


                });

    }


    private void saveUser(LoginBean bean){

        ACache aCache = ACache.get(mContext);

        aCache.put(Constant.TOKEN,bean.getToken());
        aCache.put(Constant.USER,bean.getUser());
    }
}
