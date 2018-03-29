package com.xiaoqianghe.wqplay.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.xiaoqianghe.wqplay.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Author：Wq
 * Date：2018/2/27 10:12
 * Description：//todo
 */

public class CustomImageView extends View {



    int width = 0;
    int height=0 ;


    private Bitmap mImage;
    private int mImageScale;

    /**
     * 文本
     */
    private String mTitleText;
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    private Rect rect;

    public CustomImageView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context)
    {
        this(context, null);
    }

    /**
     * 获得我自定义的样式属性
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.CustomImageView_image:
                    mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomImageView_imageScaleType:
                    mImageScale = a.getInt(attr, 0);
                    break;

                case R.styleable.CustomImageView_titleText:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.CustomImageView_titleTextColor:
                    // 默认颜色设置为黑色
                    mTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomImageView_titleTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;

            }

        }
        a.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        rect = new Rect();

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        // mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

//
//        this.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                mTitleText = randomText();
//                postInvalidate();
//
//            }
//        });

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else
        {
            // 由图片决定的宽
            int desireByImg = getPaddingLeft() + getPaddingRight() + mImage.getWidth();
            // 由字体决定的宽
            int desireByTitle = getPaddingLeft() + getPaddingRight() + mBound .width();

            if (widthMode == MeasureSpec.AT_MOST)// wrap_content
            {
                int desire = Math.max(desireByImg, desireByTitle);
                width = Math.min(desire, widthSize );
                Log.e("xxx", "AT_MOST");
            }
        }



        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
//            mPaint.setTextSize(mTitleTextSize);
//            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
//            float textHeight = mBound.height();
//            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
//            height = desired;


            int desire = getPaddingTop() + getPaddingBottom() + mImage.getHeight() + mBound.height();
            if (heightMode == MeasureSpec.AT_MOST)// wrap_content
            {
                height = Math.min(desire, heightSize);
            }
        }



        setMeasuredDimension(width, height);


    }

    @Override
    protected void onDraw(Canvas canvas)
    {
//        mPaint.setColor(Color.YELLOW);
//        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
//
//        mPaint.setColor(mTitleTextColor);
//        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);


        // super.onDraw(canvas);
        /**
         * 边框
         */
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mBound.left = getPaddingLeft();
        mBound.right = width - getPaddingRight();
        mBound.top = getPaddingTop();
        mBound.bottom = height - getPaddingBottom();

        mPaint.setColor(mTitleTextColor);
        mPaint.setStyle(Paint.Style.FILL);


        /**
         * 当前设置的宽度小于字体需要的宽度，将字体改为xxx...
         */
        if (mBound .width() > width)
        {
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitleText, paint, (float) width - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), height - getPaddingBottom(), mPaint);

        } else
        {
            //正常情况，将字体居中
            canvas.drawText(mTitleText, width / 2 - mBound .width() * 1.0f / 2, height - getPaddingBottom(), mPaint);
        }

        //取消使用掉的快
        rect.bottom -= mBound .height();


        // 这里需要判断 ImageScalType

//        if (mImageScale == IMAGE_SCALE_FITXY)
//        {
//            canvas.drawBitmap(mImage, null, rect, mPaint);
//        } else
//        {
//            //计算居中的矩形范围
//            rect.left = width / 2 - mImage.getWidth() / 2;
//            rect.right = width / 2 + mImage.getWidth() / 2;
//            rect.top = (height - mBound .height()) / 2 - mImage.getHeight() / 2;
//            rect.bottom = (height - mBound .height()) / 2 + mImage.getHeight() / 2;
//
//            canvas.drawBitmap(mImage, null, rect, mPaint);
//        }

        //            //计算居中的矩形范围
            rect.left = width / 2 - mImage.getWidth() / 2;
            rect.right = width / 2 + mImage.getWidth() / 2;
            rect.top = (height - mBound .height()) / 2 - mImage.getHeight() / 2;
            rect.bottom = (height - mBound .height()) / 2 + mImage.getHeight() / 2;

            canvas.drawBitmap(mImage, null, rect, mPaint);

    }


    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }



}
