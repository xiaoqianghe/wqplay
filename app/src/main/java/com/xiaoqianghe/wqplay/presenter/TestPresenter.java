package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.bean.requestbean.LoginBean;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ErrHandlerSubscriber;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ErrHandlerSubscriberShow;
import com.xiaoqianghe.wqplay.presenter.contract.TestContract;

import javax.inject.Inject;

/**
 * Author：Wq
 * Date：2018/7/17 18:03
 * Description：//todo
 */

public class TestPresenter extends BasePresenter<TestContract.ITestModel,TestContract.TestView> {


   @Inject
    public TestPresenter(TestContract.ITestModel mModel, TestContract.TestView mView) {
        super(mModel, mView);
    }


    public void loadData(){
       mModel.login("","")
               .compose(RxHttpResponseCompat.<LoginBean>compatResult())
               .subscribe(new ErrHandlerSubscriber<LoginBean>(mContext) {
                   @Override
                   public void onNext(LoginBean loginBean) {

                   }

                   @Override
                   public void onComplete() {

                   }
               });

       mModel.login("","")
               .compose(RxHttpResponseCompat.<LoginBean>compatResult())
               .subscribe(new ErrHandlerSubscriberShow<LoginBean>(mContext) {
                   @Override
                   public void onError(int whatRequest, Throwable e) {

                   }

                   @Override
                   public void onSuccess(int whatRequest, LoginBean loginBean) {

                   }
               });
    }





}
