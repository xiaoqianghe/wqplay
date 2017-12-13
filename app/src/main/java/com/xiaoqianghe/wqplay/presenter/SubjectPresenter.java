package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.bean.Subject;
import com.xiaoqianghe.wqplay.bean.SubjectDetail;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ErrHandlerSubscriber;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressSubscriber;
import com.xiaoqianghe.wqplay.presenter.contract.SubjectContract;

import javax.inject.Inject;

import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.Observer;


/**
 * Author：Wq
 * Date：2017/12/11 17:46
 * Description：//todo
 */

public class SubjectPresenter extends BasePresenter<SubjectContract.ISubjectModel,SubjectContract.SubjectView> {

    @Inject
    public SubjectPresenter(SubjectContract.ISubjectModel mModel, SubjectContract.SubjectView mView) {
        super(mModel, mView);
    }


    public void  getSubjects(int page){

        Observer subscriber=null;
         if(page==0){

             subscriber= new ProgressSubscriber<PageBean<Subject>>(mContext,mView) {


                 @Override
                 public void onNext(PageBean<Subject> subjectPageBean) {

                 }
             };

         }else{

             subscriber=new ErrHandlerSubscriber<PageBean<Subject>>(mContext) {

                 @Override
                 public void onNext(PageBean<Subject> subjectPageBean) {

                 }

                 @Override
                 public void onComplete() {

                 }
             };


         }

        mModel.getSubjects(page)
                .compose(RxHttpResponseCompat.<PageBean<Subject>>compatResult())
                .subscribe(subscriber);


    }


    public void  getgetSubjectDetail(int id){
        mModel.getSubjectDetail(id).compose(RxHttpResponseCompat.<SubjectDetail>compatResult())
                .subscribe(new ProgressSubscriber<SubjectDetail>(mContext,mView) {
                    @Override
                    public void onNext(SubjectDetail subjectDetail) {

                        mView.showSubjectDetail(subjectDetail);

                    }
                });
    }

}
