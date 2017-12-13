package com.xiaoqianghe.wqplay.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.Subject;

import java.util.List;

/**
 * Author：Wq
 * Date：2017/12/12 11:45
 * Description：//todo
 */

public class SubjectAdapter extends BaseQuickAdapter<Subject,BaseViewHolder> {
    public SubjectAdapter() {
        super(R.layout.template_imageview);
    }

    @Override
    protected void convert(BaseViewHolder helper, Subject item) {

    }
}
