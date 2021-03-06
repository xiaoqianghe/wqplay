package com.xiaoqianghe.wqplay.presenter;

import android.graphics.pdf.PdfDocument;
import android.util.Log;

import com.google.gson.Gson;
import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.bean.requestbean.PageBean;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ErrHandlerSubscriber;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressDialogSubscriber;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressSubscriber;
import com.xiaoqianghe.wqplay.data.AppInfoModel;
import com.xiaoqianghe.wqplay.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import zlc.season.rxdownload2.RxDownload;
//import rx.Observable;
//import rx.Subscriber;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * @包名: com.xiaoqianghe.wqplay.presenter
 * @类名: AppInfoPresenter
 * @创建人: xiaoqianghe
 * @创建时间 : 2017/4/5 11:16
 * @描述 : TODO
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel,AppInfoContract.AppInfoView> {

    public static final String TAG = "AppInfoPresenter";


    public static final int  TOP_LIST=1;
    public static final int  GAME=2;
    public static final int  CATEGORY=3;


    public static final int FEATURED=0;
    public static final int TOPLIST=1;
    public static final int NEWLIST=2;


    public static final int HOT_APP_LIST=3;

//    @Inject
//    RxDownload mRxdownload;

    @Inject
    public AppInfoPresenter(AppInfoModel mModel, AppInfoContract.AppInfoView mView) {
        super(mModel, mView);
    }

    public void requestData(int type,int page,int categoryId,int flagType){

//        Subscriber mSubscriber=null;
//
//        if(page==0){
//            //第一页显示loading
//
//            mSubscriber=new ProgressDialogSubscriber<PageBean<AppInfo>>(mContext,mView) {
//
//                @Override
//                public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                    Log.d(TAG,"=======page==0::"+new Gson().toJson(appInfoPageBean));
//                    mView.showResultData(appInfoPageBean);
//
//                }
//            };
//
//        }else{
//
//            //加载下一页
//
//            mSubscriber=new ErrHandlerSubscriber<PageBean<AppInfo>>(mContext) {
//
//                @Override
//                public void onCompleted() {
//
//                    mView.onLoadMoreComplete();
//
//                }
//
//                @Override
//                public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                    mView.showResultData(appInfoPageBean);
//                }
//            };
//
//        }





        Observer mSubscriber =null;

        if(page==0){
            //第一页显示loading

            mSubscriber=new ProgressSubscriber<PageBean<AppInfo>>(mContext,mView) {

                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    Log.d(TAG,"=======page==0::"+new Gson().toJson(appInfoPageBean));
                    mView.showResultData(appInfoPageBean);

                }
            };

        }else{

            //加载下一页

            mSubscriber=new ErrHandlerSubscriber<PageBean<AppInfo>>(mContext) {



                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResultData(appInfoPageBean);
                }

                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();

                }
            };

        }

//
//        Observable observable=getObservable(type,page);
//
//        observable.compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
//
//                .subscribe(mSubscriber);

        Observable observable = getObservable(type,page,categoryId,flagType);
        observable.compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(mSubscriber);



    }


//
//    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type,int page){
//        switch (type){
//
//            case TOP_LIST:
//                return mModel.topList(page);
//            case GAME:
//                return  mModel.games(page);
//            default:
//                return Observable.empty();
//
//        }
//
//    }



    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type, int page, int categoryId, int flagType){

        switch (type){

            case TOP_LIST:
                return  mModel.topList(page);


            case GAME:
                return  mModel.games(page);

            case CATEGORY:

                if(flagType==FEATURED){

                    return  mModel.getFeaturedAppsByCategory(categoryId,page);
                }

                else  if(flagType==TOPLIST){

                    return  mModel.getTopListAppsByCategory(categoryId,page);
                }

                else  if(flagType==NEWLIST){

                    return  mModel.getNewListAppsByCategory(categoryId,page);
                }



            default:
                return Observable.empty();
        }

    }




    public void  requestData(int type,int page){

        requestData(type,page,0,0);

    }


    public void requestCategoryApps(int categoryId,int page,int flagType){


        requestData(CATEGORY,page,categoryId,flagType);


    }

}
