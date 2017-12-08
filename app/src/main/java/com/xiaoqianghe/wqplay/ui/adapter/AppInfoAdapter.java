package com.xiaoqianghe.wqplay.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.common.imageloader.ImageLoader;


import java.util.List;

import zlc.season.rxdownload2.RxDownload;

/**
 * Author：Wq
 * Date：2017/6/29 15:27
 * Description：//todo
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder> {

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    private Builder mBuilder;
    private AppInfoAdapter(Builder builder){
        super(R.layout.template_appinfo);
        this.mBuilder=builder;

        openLoadAnimation();
    }

    public static Builder builder(){

        return new Builder();
    }




    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {

        ImageLoader.load(baseImgUrl+item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));

        helper.setText(R.id.txt_app_name,item.getDisplayName())
                .setText(R.id.txt_brief,item.getBriefShow());


        TextView txtViewPosition = helper.getView(R.id.txt_position);
        txtViewPosition.setVisibility(mBuilder.isShowPosition? View.VISIBLE:View.GONE);
        txtViewPosition.setText(item.getPosition()+1 +". ");

        TextView txtViewCategory = helper.getView(R.id.txt_category);
        txtViewCategory.setVisibility(mBuilder.isShowCategoryName?View.VISIBLE:View.GONE);
        txtViewCategory.setText(item.getLevel1CategoryName());

        TextView txtViewBrief = helper.getView(R.id.txt_brief);
        txtViewBrief.setVisibility(mBuilder.isShowBrief?View.VISIBLE:View.GONE);
        txtViewBrief.setText(item.getBriefShow());

    }



    public static class  Builder{




        private boolean isShowPosition;
        private boolean isShowCategoryName;
        private boolean isShowBrief;



        private boolean isUpdateStatus;

        private RxDownload mRxDownload;


        private int layoutId=R.layout.template_appinfo;






        public Builder showPosition(boolean b){

            this.isShowPosition =b;
            return this;
        }


        public Builder showCategoryName(boolean b){

            this.isShowCategoryName =b;
            return this;
        }


        public Builder showBrief(boolean b){

            this.isShowBrief =b;
            return this;
        }

        public AppInfoAdapter build(){


            return  new AppInfoAdapter(this);
        }


        public Builder layout(int resId){
            this.layoutId = resId;
            return this;
        }


        public Builder rxDownload(RxDownload rxDownload){
            this.mRxDownload = rxDownload;
            return this;
        }
        public Builder updateStatus(boolean b){
            this.isUpdateStatus = b;
            return this;
        }







    }
}
