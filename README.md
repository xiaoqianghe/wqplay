
//
一.ProgressDialogSubcribers 的创建和使用

二.ProgressFragment 的统一处理


19-24:遗留问题: 关于ProgressFragment 统一处理情况下的empty问题
1.解决了empty问题




1. 客服---------
1： cainiaowo

http://112.124.22.238:8081/course_api/cniaoplay/index?p={%22publicParams%22:{%22la%22:%22zh%22,%22os%22:%221.3.1047.0621%22,%22model%22:%22SAMSUNG-SM-N900A%22,%22sdk%22:%2219%22,%22resolution%22:%221080*1920%22,%22densityScaleFactor%22:%222.0%22,%22imei%22:%22180990412310873%22}}


http://112.124.22.238:8081/course_api/cniaoplay/toplist?p={%22publicParams%22:{%22la%22:%22zh%22,%22os%22:%221.3.1047.0621%22,%22model%22:%22SAMSUNG-SM-N900A%22,%22sdk%22:%2219%22,%22resolution%22:%221080*1920%22,%22densityScaleFactor%22:%222.0%22,%22imei%22:%22180990412310873%22},%22page%22:%220%22}


2：me：

http://112.124.22.238:8081/course_api/cniaoplay/featured2?p={%27page%27:0}

http://112.124.22.238:8081/course_api/cniaoplay/toplist?page=0


Error:(25, 23) 错误: android.app.Application cannot be provided without an @Inject constructor or from an @Provides-annotated method.
com.xiaoqianghe.wqplay.di.module.HttpModule.provideApiService(retrofit2.Retrofit retrofit)
[parameter: retrofit2.Retrofit retrofit]
com.xiaoqianghe.wqplay.di.module.HttpModule.provideRetrofit(okhttp3.OkHttpClient okHttpClient)
[parameter: okhttp3.OkHttpClient okHttpClient]
com.xiaoqianghe.wqplay.di.module.HttpModule.provideOkHttpClient(android.app.Application application, com.google.gson.Gson gson)
[parameter: android.app.Application application]












