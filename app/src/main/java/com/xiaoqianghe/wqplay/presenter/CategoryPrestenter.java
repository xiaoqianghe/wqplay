package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.bean.requestbean.Category;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressDialogSubscriber;
import com.xiaoqianghe.wqplay.presenter.contract.CategoryContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Author：Wq
 * Date：2017/9/18 14:51
 * Description：//todo
 */

public class CategoryPrestenter extends BasePresenter<CategoryContract.ICageoryModel,CategoryContract.CategoryView> {


    @Inject
    public CategoryPrestenter(CategoryContract.ICageoryModel mModel, CategoryContract.CategoryView mView) {
        super(mModel, mView);
    }



    public void  getAllCategory(){

        mModel.getCategories().compose(RxHttpResponseCompat.<List<Category>>compatResult())
                .subscribe(new ProgressDialogSubscriber<List<Category>>(mContext,mView) {
                    @Override
                    public void onNext(List<Category> categories) {
                        mView.showData(categories);
                    }
                });

    }







}
