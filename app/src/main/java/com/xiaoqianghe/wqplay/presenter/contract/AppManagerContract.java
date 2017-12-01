package com.xiaoqianghe.wqplay.presenter.contract;

import com.xiaoqianghe.wqplay.common.apkparset.AndroidApk;
import com.xiaoqianghe.wqplay.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Author：Wq
 * Date：2017/11/30 14:44
 * Description：//todo
 */

public interface AppManagerContract {

    interface  AppManagerView extends BaseView{

        void showDownloading(List<DownloadRecord> downloadRecords);

        void showApps(List<AndroidApk> androidApks);

    }

    interface  IAppManagerModel{

        Observable<List<DownloadRecord>> getDownloadRecord();
        RxDownload getRxDownload();
        Observable<List<AndroidApk>> getLocalApks();
    }
}
