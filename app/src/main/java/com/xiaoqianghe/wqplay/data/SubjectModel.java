package com.xiaoqianghe.wqplay.data;

import com.xiaoqianghe.wqplay.bean.Subject;
import com.xiaoqianghe.wqplay.bean.SubjectDetail;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.contract.SubjectContract;

import io.reactivex.Observable;

/**
 * Author：Wq
 * Date：2017/12/13 15:25
 * Description：//todo
 */

public class SubjectModel implements SubjectContract.ISubjectModel {

    private ApiService mApiService;

    public SubjectModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseBean<PageBean<Subject>>> getSubjects(int page) {
        return mApiService.subjects(page);
    }

    @Override
    public Observable<BaseBean<SubjectDetail>> getSubjectDetail(int id) {
        return mApiService.subjectDetail(id);
    }
}
