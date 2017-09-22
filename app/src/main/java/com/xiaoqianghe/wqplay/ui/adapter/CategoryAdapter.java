package com.xiaoqianghe.wqplay.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.requestbean.Category;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.common.imageloader.ImageLoader;

/**
 * Author：Wq
 * Date：2017/9/18 15:02
 * Description：//todo
 */

public class CategoryAdapter extends BaseQuickAdapter<Category,BaseViewHolder> {
    public CategoryAdapter() {
        super(R.layout.template_category);
    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {

        helper.setText(R.id.text_name,item.getName());


        ImageLoader.load(Constant.BASE_IMG_URL+item.getIcon(),(ImageView)helper.getView(R.id.imgView));

    }
}
