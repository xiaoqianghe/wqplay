package com.xiaoqianghe.wqplay.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @包名: com.xiaoqianghe.wqplay.ui.activity
 * @类名: AppInfoActivity
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/3/27 19:37
 * @描述 : TODO
 */

public class AppInfoActivity extends BaseActivity {


    @BindView(R.id.bt_togetInfo)
    Button btTogetInfo;
    @BindView(R.id.tv_info)
    TextView tvInfo;

    @Override
    protected void init() {

        btTogetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_appinfo;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
