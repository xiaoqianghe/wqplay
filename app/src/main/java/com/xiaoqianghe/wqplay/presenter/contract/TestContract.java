package com.xiaoqianghe.wqplay.presenter.contract;

import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.LoginBean;
import com.xiaoqianghe.wqplay.ui.BaseView;

import io.reactivex.Observable;

/**
 * Author：Wq
 * Date：2018/7/17 17:51
 * Description：//todo
 */

public class TestContract {


    public  interface TestView extends BaseView{

        void loadDataSuccess();

    }


    public interface  ITestModel {
        Observable<BaseBean<LoginBean>> login(String phone, String pwd);
    }



}
