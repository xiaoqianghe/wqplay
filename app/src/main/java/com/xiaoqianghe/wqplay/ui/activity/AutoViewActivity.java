package com.xiaoqianghe.wqplay.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.ui.BaseView;
import com.xiaoqianghe.wqplay.ui.widget.ViewGroupContainer.GestureLockViewGroup;

/**
 * Author：Wq
 * Date：2018/2/27 11:13
 * Description：//todo
 */

public class AutoViewActivity extends AppCompatActivity{

    private GestureLockViewGroup mGestureLockViewGroup;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_autoview);

        setContentView(R.layout.activity_gesturelock);



        mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
        mGestureLockViewGroup.setAnswer(new int[] { 1, 2, 3, 4,5 });
        mGestureLockViewGroup
                .setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener()
                {

                    @Override
                    public void onUnmatchedExceedBoundary()
                    {
                        Toast.makeText(AutoViewActivity.this, "错误5次...",
                                Toast.LENGTH_SHORT).show();
                        mGestureLockViewGroup.setUnMatchExceedBoundary(5);
                    }

                    @Override
                    public void onGestureEvent(boolean matched)
                    {
//                        Toast.makeText(AutoViewActivity.this, matched+"",
//                                Toast.LENGTH_SHORT).show();

                        Toast.makeText(AutoViewActivity.this, "图案密码解锁："+matched,
                                Toast.LENGTH_SHORT).show();

                        mGestureLockViewGroup.reset();
                    }

                    @Override
                    public void onBlockSelected(int cId)
                    {
                    }
                });
    }
}
