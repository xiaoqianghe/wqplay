package com.xiaoqianghe.wqplay.presenter.contract;

import com.xiaoqianghe.wqplay.bean.Subject;
import com.xiaoqianghe.wqplay.bean.SubjectDetail;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.ui.BaseView;

import io.reactivex.Observable;

/**
 * Author：Wq
 * Date：2017/12/11 17:14
 * Description：//todo
 */

public interface SubjectContract {


    public interface  SubjectView  extends BaseView{

        void showSubject(PageBean<Subject> subjects);

        void onLoadMoreComplete();

        void showSubjectDetail();


    }


    public interface ISubjectModel {

        Observable<BaseBean<PageBean<Subject>>> getSubjects(int page);

        Observable<BaseBean<SubjectDetail>> getSubjectDetail(int id);
    }

}
