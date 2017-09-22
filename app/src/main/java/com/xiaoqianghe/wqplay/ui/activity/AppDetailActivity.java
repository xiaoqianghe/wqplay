package com.xiaoqianghe.wqplay.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.common.imageloader.ImageLoader;
import com.xiaoqianghe.wqplay.common.util.DensityUtil;
import com.xiaoqianghe.wqplay.di.component.AppComponent;
import com.xiaoqianghe.wqplay.presenter.AppDetailPresenter;
import com.xiaoqianghe.wqplay.ui.Fragment.AppDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：Wq
 * Date：2017/9/20 9:59
 * Description：//todo
 */

public class AppDetailActivity extends BaseActivity<AppDetailPresenter> {
    @BindView(R.id.view_temp)
    View viewTemp;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.view_content)
    FrameLayout viewContent;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.view_coordinator)
    CoordinatorLayout viewCoordinator;

    private AppInfo mAppInfo;

    @Override
    protected void init() {

        mAppInfo=(AppInfo)getIntent().getSerializableExtra("appinfo");


        ImageLoader.load(Constant.BASE_IMG_URL+mAppInfo.getIcon(),imgIcon);

        txtName.setText(mAppInfo.getDisplayName());

        toolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        View view = mApplication.getView();
        Bitmap bitmap = getViewImageCache(view);

        if(bitmap!=null){

            viewTemp.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];




        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(viewTemp.getLayoutParams());

        marginLayoutParams.topMargin=top- DensityUtil.getStatusBarH(this);
        marginLayoutParams.leftMargin = left;
        marginLayoutParams.width = view.getWidth();
        marginLayoutParams.height =view.getHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(marginLayoutParams);

        viewTemp.setLayoutParams(params);


        open();




    }

    @Override
    protected int setLayout() {
        return R.layout.activity_app_detail;
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


    private Bitmap getViewImageCache(View view) {


        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        if (bitmap == null)
            return null;


        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());

        view.destroyDrawingCache();

        return bitmap;


    }



    private void open(){


        int h = DensityUtil.getScreenH(this) ;



        ObjectAnimator animator = ObjectAnimator.ofFloat(viewTemp,"scaleY",1f,(float) h);

        animator.setStartDelay(500);
        animator.setDuration(1000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                viewTemp.setBackgroundColor(getResources().getColor(R.color.white));


            }

            @Override
            public void onAnimationEnd(Animator animation) {


                viewTemp.setVisibility(View.GONE);
                viewCoordinator.setVisibility(View.VISIBLE);


                initFragment();

            }
        });


        animator.start();




    }


    private void initFragment(){


        AppDetailFragment fragment = new AppDetailFragment(mAppInfo.getId());

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.view_content,fragment);
        transaction.commitAllowingStateLoss();



    }

}
