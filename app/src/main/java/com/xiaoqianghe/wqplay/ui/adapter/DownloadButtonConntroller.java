package com.xiaoqianghe.wqplay.ui.adapter;

/**
 * Author：Wq
 * Date：2017/9/22 11:48
 * Description：//todo
 */

public class DownloadButtonConntroller {





//
//    public static void  handClick(final DownloadProgressButton btn, final AppInfo appInfo){
//
//
//
//        isAppInstalled(btn.getContext(),appInfo)
//
//                .flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
//                    @Override
//                    public ObservableSource<DownloadEvent> apply(@NonNull DownloadEvent event)
//                            throws Exception {
//
//                        if(DownloadFlag.UN_INSTALL == event.getFlag()){
//
//                            return  isApkFileExsit(appInfo);
//
//                        }
//                        return  Observable.just(event);
//
//
//                    }
//                })
//                .flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
//                    @Override
//                    public ObservableSource<DownloadEvent> apply(@NonNull DownloadEvent event) throws Exception {
//
//
//                        if(DownloadFlag.FILE_EXIST == event.getFlag()){
//
//                            return  getAppDownloadInfo(appInfo).flatMap(new Function<AppDownloadInfo, ObservableSource<DownloadEvent>>() {
//                                @Override
//                                public ObservableSource<DownloadEvent> apply(@NonNull AppDownloadInfo appDownloadInfo) throws Exception {
//
//
//                                    return receiveDownloadStatus(appDownloadInfo);
//                                }
//                            });
//
//                        }
//
//
//                        return Observable.just(event);
//                    }
//                })
//                .subscribe(new Consumer<DownloadEvent>() {
//                    @Override
//                    public void accept(@NonNull DownloadEvent event) throws Exception {
//
//
//                        int flag = event.getFlag();
//
//                        switch (flag){
//
//                            case DownloadFlag.INSTALLED:
//
//                                btn.setText("运行");
//                                break;
//                            case DownloadFlag.STARTED:
//
//                                btn.setProgress((int) event.getDownloadStatus().getPercentNumber());
//
//                                break;
//
//                            case DownloadFlag.PAUSED:
//                                btn.paused();
//                                break;
//
//
//                            case DownloadFlag.NORMAL:
//                                btn.download();
//
//                                break;
//
//
//
//                        }
//
//                    }
//                });
//
//
//
//
//
//    }
}
