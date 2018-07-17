package com.eeepay.libbase.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.Rect;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.eeepay.libbase.widget.webview.Webview;
import com.tencent.smtt.sdk.WebView;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by ixzus on 2018/1/2.
 * Email: iandroid@foxmail.com
 * Desc:
 */

public class ScreenShot {
    private static final String TAG = "ScreenShot";

    private static boolean isX5Enabled(Webview webview) {
        if (webview.getX5WebViewExtension() != null)
            return true;
        return false;
    }

    public static void capture(Webview webview) {
        // 有x5内核没有生效，并且Android版本是5.0及以上时，调用enableSlowWholeDocumentDraw()方便截取长图
        if (!isX5Enabled(webview) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            android.webkit.WebView.enableSlowWholeDocumentDraw();
        }
        try {
            Bitmap bitmap = captureWebView(webview);
            boolean flag = Image.saveImageToGallery(webview.getContext(), bitmap);
            Log.e(TAG, "capture: " + flag);
            Toast.makeText(webview.getContext(), "图片保存成功", Toast.LENGTH_SHORT).show();
//            saveBitmap(bitmap);
        /* 对拿到的bitmap根据需要进行处理 */
        } catch (OutOfMemoryError oom) {
        /* 对OOM做处理 */
        }
    }

    public static Bitmap captureAsBitmap(Webview webview) {
        // 有x5内核没有生效，并且Android版本是5.0及以上时，调用enableSlowWholeDocumentDraw()方便截取长图
        if (!isX5Enabled(webview) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            android.webkit.WebView.enableSlowWholeDocumentDraw();
        }
        try {
            Bitmap bitmap = captureWebView(webview);
            return bitmap;
        /* 对拿到的bitmap根据需要进行处理 */
        } catch (OutOfMemoryError oom) {
        /* 对OOM做处理 */
            return null;
        }
    }


    /**
     * 对WebView进行截屏，虽然使用过期方法，
     * 但在当前Android版本中测试可行
     *
     * @param webView
     * @return
     */
    private static Bitmap captureWebView(WebView webView) {
        Log.e(TAG, "onClick: captureWebView");
        int ll = (int)(DP.dip2px(webView.getContext(), 38f));
        Picture picture = webView.capturePicture();
        int width = picture.getWidth();
        int height = picture.getHeight();
        if (width > 0 && height > 0) {
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            picture.draw(canvas);
            Bitmap bitmapTem = Bitmap.createBitmap(bitmap, 0, ll, width, height - ll);
            return bitmapTem;
        }
        Log.e(TAG, "onClick: captureWebView  null");
        return null;
    }


    private static void saveBitmap(Bitmap bitmap) {
        Log.e(TAG, "onClick: saveBitmap ");
        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        Log.e(TAG, "onClick: saveBitmap " + file.getAbsolutePath() + file.getName());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.flush();
            fos.close();
            Log.e(TAG, "onClick: saveBitmap sucess");
        } catch (java.io.IOException e) {
            e.printStackTrace();
            Log.e(TAG, "onClick: saveBitmap err");
        }
    }

    /**
     * 根据指定的Activity截图（带空白的状态栏）
     *
     * @param context 要截图的Activity
     * @return Bitmap
     */
    public static Bitmap shotActivity(Activity context) {
        View view = context.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
        return bitmap;
    }

    /**
     * 根据指定的Activity截图（去除状态栏）
     *
     * @param activity 要截图的Activity
     * @return Bitmap
     */
    public Bitmap shotActivityNoStatusBar(Activity activity) {
        // 获取windows中最顶层的view
        View view = activity.getWindow().getDecorView();
        view.buildDrawingCache();

        // 获取状态栏高度
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int statusBarHeights = rect.top;
        Display display = activity.getWindowManager().getDefaultDisplay();

        // 获取屏幕宽和高
        int widths = display.getWidth();
        int heights = display.getHeight();

        // 允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);

        // 去掉状态栏
        Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0,
                statusBarHeights, widths, heights - statusBarHeights);

        // 销毁缓存信息
        view.destroyDrawingCache();

        return bmp;
    }

    /**
     * 根据指定的view截图
     *
     * @param v 要截图的view
     * @return Bitmap
     */
    public static Bitmap getViewBitmap(View v) {
        if (null == v) {
            return null;
        }
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        if (Build.VERSION.SDK_INT >= 11) {
            v.measure(View.MeasureSpec.makeMeasureSpec(v.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(v.getHeight(), View.MeasureSpec.EXACTLY));
            v.layout((int) v.getX(), (int) v.getY(), (int) v.getX() + v.getMeasuredWidth(), (int) v.getY() + v.getMeasuredHeight());
        } else {
            v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        }

        Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache(), 0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        v.setDrawingCacheEnabled(false);
        v.destroyDrawingCache();
        return bitmap;
    }

    /**
     * Scrollview截屏
     *
     * @param scrollView 要截图的ScrollView
     * @return Bitmap
     */
    public static Bitmap shotScrollView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

}
