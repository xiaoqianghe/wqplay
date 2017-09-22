package com.xiaoqianghe.wqplay.presenter.contract;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.Category;
import com.xiaoqianghe.wqplay.ui.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Author：Wq
 * Date：2017/6/1 16:11
 * Description：//todo
 */

public interface CategoryContract {

    public interface ICageoryModel{

        Observable<BaseBean<List<Category>>> getCategories();
    }


    public interface CategoryView extends BaseView{

        public void showData(List<Category> categories);
    }




}
