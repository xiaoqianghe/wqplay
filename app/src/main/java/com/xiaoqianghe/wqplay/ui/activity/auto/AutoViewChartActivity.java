package com.xiaoqianghe.wqplay.ui.activity.auto;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.ui.widget.ViewGroupContainer.GestureLockViewGroup;

import java.util.ArrayList;

/**
 * Author：Wq
 * Date：2018/2/27 11:13
 * Description：//todo
 */

public class AutoViewChartActivity extends AppCompatActivity{
    private LineChart mChart;

    LineChart[] mCharts = new LineChart[4]; // 4条数据
    Typeface mTf; // 自定义显示字体
    int[] mColors = new int[] { Color.rgb(137, 230, 81), Color.rgb(240, 240, 30),//
            Color.rgb(89, 199, 250), Color.rgb(250, 104, 104) }; // 自定义颜色




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autoview_chart);

        mChart = (LineChart) findViewById(R.id.chart1);

//        setup(mChart);

//        mChart.getAxisLeft().setAxisMaximum(150f);
//        mChart.getAxisLeft().setAxisMinimum(0f);

        mChart.getAxisLeft().setAxisMaxValue(150f);
        mChart.getAxisLeft().setAxisMinValue(0f);
        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getXAxis().setDrawGridLines(false);


//        mChart.setOnChartGestureListener(this);
//        mChart.setOnChartValueSelectedListener(this);
//        mChart.setDrawGridBackground(false);
//
//        // no description text
        mChart.setDescription("");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");
//
        // enable touch gestures
        mChart.setTouchEnabled(true);//是否可以触摸
//
//        // enable scaling and dragging
        mChart.setDragEnabled(true);//是否可以拖拽
        mChart.setScaleEnabled(true);//是否可以缩放
        // mChart.setScaleXEnabled(true);//
        // mChart.setScaleYEnabled(true);
//
//        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);
//
//        // set an alternative background color
//        // mChart.setBackgroundColor(Color.GRAY);
//
//        // create a custom MarkerView (extend MarkerView) and specify the layout
//        // to use for it
//        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
//
//        // set the marker to the chart
//        mChart.setMarkerView(mv);




        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend(); // 设置标示，就是那个一组y的value的

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.CIRCLE);// 样式
        l.setFormSize(6f);// 字体
        l.setTextColor(Color.WHITE);// 颜色
        l.setTypeface(mTf);// 字体


//        mChart.get
//
//        YLabels y = chart.getYLabels(); // y轴的标示
//        y.setTextColor(Color.WHITE);
//        y.setTypeface(mTf);
//        y.setLabelCount(4); // y轴上的标签的显示的个数
//
//        XLabels x = chart.getXLabels(); // x轴显示的标签
//        x.setTextColor(Color.WHITE);
//        x.setTypeface(mTf);
//
//        // animate calls invalidate()...
//        chart.animateX(2500); // 立即执行的动画,x轴





    }

    @Override
    protected void onResume() {
        super.onResume();
        setData(30,100);
    }

    /**
     * 设置模拟数据
     * @param count 模拟的个数
     * @param range 数据的范围
     */
    private void setData(int count, float range) {
        ArrayList<Entry> values = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            // set the line to be drawn like this "- - - - - -"
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);

            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            }
            else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mChart.setData(data);
        }
    }

}
