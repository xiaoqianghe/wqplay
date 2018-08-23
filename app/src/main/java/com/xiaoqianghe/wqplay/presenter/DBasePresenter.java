package com.xiaoqianghe.wqplay.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.xiaoqianghe.wqplay.ui.BaseView;
import com.xiaoqianghe.wqplay.ui.DBaseView;

/**
 * @包名: com.xiaoqianghe.wqplay.presenter
 * @类名: BasePresenter
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/3/27 17:09
 * @描述 : TODO 所有Presenter的基类
 */

public class DBasePresenter<M,V extends DBaseView> {

    protected M mModel;
    protected V mView;

    protected Context mContext;

    public DBasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;

        initContext();
    }

    private void initContext() {
        if(mView instanceof Fragment){
            mContext=((Fragment) mView).getActivity();
        }else{
            mContext=(Activity)mView;
        }
    }
}
