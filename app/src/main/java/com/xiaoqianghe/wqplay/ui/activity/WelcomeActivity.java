package com.xiaoqianghe.wqplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.common.Constant;

import com.xiaoqianghe.wqplay.common.util.ACache;

import butterknife.ButterKnife;

/**
 * Author：Wq
 * Date：2017/6/1 15:33
 * Description：//todo
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        jump();
    }

    /**
     * @todo: 跳转页面
     *
     *
     *
     * */
    private void jump() {

        String isShowGuide=ACache.get(this).getAsString(Constant.IS_SHOW_GUIDE);

        //第一次启动进入引导页

        if(null==isShowGuide){
            //跳转成功页面
            startActivity(new Intent(this,GuideActivity.class));
        }else{
            //跳转首页
            startActivity(new Intent(this,MainActivity.class));

        }
    }
}
