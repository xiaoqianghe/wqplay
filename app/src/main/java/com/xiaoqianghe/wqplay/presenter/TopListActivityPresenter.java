package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.bean.requestbean.IndexBean;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressNewDialogSubscriber;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressSubscriber;
import com.xiaoqianghe.wqplay.data.TopListActivityModel;
import com.xiaoqianghe.wqplay.presenter.contract.TopListActivityContract;

import javax.inject.Inject;

/**
 * Author：Wq
 * Date：2018/8/16 11:41
 * Description：//todo
 */

public class TopListActivityPresenter extends BasePresenter<TopListActivityModel,TopListActivityContract.ITestListActivityView> {

    @Inject
    public TopListActivityPresenter(TopListActivityModel mModel, TopListActivityContract.ITestListActivityView mView) {
        super(mModel, mView);
    }


    public void requestData(){

//        mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult())
//                .subscribe(new ProgressSubscriber<IndexBean>(mContext,mView) {
//                    @Override
//                    public void onNext(IndexBean indexBean) {
//                        mView.showResultData(indexBean);
//                    }
//                });




//

        mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressNewDialogSubscriber<IndexBean>(mContext,mView) {
                    @Override
                    public void onNext(IndexBean indexBean) {
//                        super.onNext(indexBean);
                        mView.showResultData(indexBean);
                    }
                });


    }


}
