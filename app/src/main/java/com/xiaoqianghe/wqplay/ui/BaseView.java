package com.xiaoqianghe.wqplay.ui;

/**
 * @包名: com.xiaoqianghe.wqplay
 * @类名: BaseView
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/3/27 17:04
 * @描述 : TODO 所有类的基类
 */

public interface BaseView {


    void showLoading();
    void dismissLoading();
    void showError(String str);


}
