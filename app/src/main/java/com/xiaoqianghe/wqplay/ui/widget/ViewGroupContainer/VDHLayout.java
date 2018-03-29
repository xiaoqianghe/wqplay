package com.xiaoqianghe.wqplay.ui.widget.ViewGroupContainer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Author：Wq
 * Date：2018/2/28 11:27
 * Description：//todo
 */

public class VDHLayout extends LinearLayout {
    private ViewDragHelper mDragger;

    public VDHLayout(Context context) {
        super(context);
    }

    public VDHLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback()
        {
            @Override
            public boolean tryCaptureView(View child, int pointerId)
            {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx)
            {
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy)
            {
                return top;
            }
        });







    }



}
