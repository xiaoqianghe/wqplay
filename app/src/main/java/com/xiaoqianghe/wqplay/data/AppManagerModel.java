package com.xiaoqianghe.wqplay.data;

import android.content.Context;

import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.common.apkparset.AndroidApk;
import com.xiaoqianghe.wqplay.common.util.ACache;
import com.xiaoqianghe.wqplay.presenter.contract.AppManagerContract;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Author：Wq
 * Date：2017/11/30 17:55
 * Description：//todo
 */

public class AppManagerModel implements AppManagerContract.IAppManagerModel {

    private RxDownload  mRxDownload;
    private Context mContext;


    public AppManagerModel(RxDownload mRxDownload, Context mContext) {
        mRxDownload = mRxDownload;
        this.mContext = mContext;
    }

    @Override
    public Observable<List<DownloadRecord>> getDownloadRecord() {
        return mRxDownload.getTotalDownloadRecords();
    }

    @Override
    public RxDownload getRxDownload() {
        return mRxDownload;
    }

    @Override
    public Observable<List<AndroidApk>> getLocalApks() {
//        return null;

        final  String dir= ACache.get(mContext).getAsString(Constant.APK_DOWNLOAD_DIR);

        return Observable.create(new ObservableOnSubscribe<List<AndroidApk>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AndroidApk>> e) throws Exception {
                e.onNext(scanApks());
                e.onComplete();
            }
        });
    }


    private List<AndroidApk> scanApks(String dir){


        File file = new File(dir);


        if(!file.isDirectory()){

            throw  new RuntimeException("is not Dir");
        }



        File[] apks =  file.listFiles(new FileFilter(){


            @Override
            public boolean accept(File f) {


                if(f.isDirectory()){
                    return  false;
                }

                return f.getName().endsWith(".apk");
            }
        });


        List<AndroidApk>  androidApks = new ArrayList<>();


        for (File apk : apks){

            AndroidApk androidApk = AndroidApk.read(mContext,apk.getPath());

            androidApks.add(androidApk);
        }


        return androidApks;









    }

}
