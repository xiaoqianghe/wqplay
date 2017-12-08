package com.xiaoqianghe.wqplay.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoqianghe.wqplay.R;

import java.util.List;

/**
 * Author：Wq
 * Date：2017/12/5 15:32
 * Description：//todo
 */

public class SearchHistoryAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public SearchHistoryAdapter() {
        super(R.layout.template_search_history);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.btn,item);

        helper.addOnClickListener(R.id.btn);

    }
}
