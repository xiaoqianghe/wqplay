package com.xiaoqianghe.wqplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Author：Wq
 * Date：2017/6/7 14:29
 * Description：//todo
 */

public class RecomendAppAdapter extends RecyclerView.Adapter<RecomendAppAdapter.ViewHolder> {

    private Context mContext;
    private List<AppInfo> mDatas;
    private LayoutInflater mLayoutInflater;

    public RecomendAppAdapter(List<AppInfo> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mLayoutInflater=LayoutInflater.from(mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.template_recomend_app, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo appInfo = mDatas.get(position);

//        holder.mImgIcon

        String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";



    }

    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
