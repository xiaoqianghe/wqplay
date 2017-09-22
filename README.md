
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




http://112.124.22.238:8081/course_api/cniaoplay/index?p={%22publicParams%22:{%22la%22:%22zh%22,%22os%22:%221.3.1047.0621%22,%22model%22:%22SAMSUNG-SM-N900A%22,%22sdk%22:%2219%22,%22resolution%22:%221080*1920%22,%22densityScaleFactor%22:%222.0%22,%22imei%22:%22180990412310873%22}}


http://112.124.22.238:8081/course_api/cniaoplay/toplist?p={%22publicParams%22:{%22la%22:%22zh%22,%22os%22:%221.3.1047.0621%22,%22model%22:%22SAMSUNG-SM-N900A%22,%22sdk%22:%2219%22,%22resolution%22:%221080*1920%22,%22densityScaleFactor%22:%222.0%22,%22imei%22:%22180990412310873%22},%22page%22:%220%22}


http://112.124.22.238:8081/course_api/cniaoplay/game?p={%22publicParams%22:{%22la%22:%22zh%22,%22os%22:%221.3.1047.0621%22,%22model%22:%22SAMSUNG-SM-N900A%22,%22sdk%22:%2219%22,%22resolution%22:%221080*1920%22,%22densityScaleFactor%22:%222.0%22,%22imei%22:%22180990412310873%22},%22page%22:%220%22}


http://112.124.22.238:8081/course_api/cniaoplay/toplist?page=0?p={%22publicParams%22:{%22la%22:%22zh%22,%22os%22:%221.3.1047.0621%22,%22model%22:%22SAMSUNG-SM-N900A%22,%22sdk%22:19,%22resolution%22:%221080*1920%22,%22densityScaleFactor%22:%222.0%22,%22imei%22:%22180990412310873%22},%22page%22:%220%22}




Error:(21, 10) 错误: com.xiaoqianghe.wqplay.data.AppInfoModel cannot be provided without an @Inject constructor or from an @Provides-annotated method.
com.xiaoqianghe.wqplay.ui.Fragment.ProgressDialogFragment.mPresenter
[injected field of type: com.xiaoqianghe.wqplay.presenter.RecommendPresenter mPresenter]
com.xiaoqianghe.wqplay.presenter.RecommendPresenter.<init>(com.xiaoqianghe.wqplay.data.AppInfoModel mModel, com.xiaoqianghe.wqplay.presenter.contract.AppInfoContract.View mView)
[parameter: com.xiaoqianghe.wqplay.data.AppInfoModel mModel]















0410:待支付      没有支付方式
0411:加价待支付   有一个支付方式                            已支付  (微信支付)23
0412:报价中       支付了询价费之后有一个支付方式             询价服务费     （微信支付)123
0413:报价待支付    支付了询价费之后   有一个支付方式          询价服务费     （微信支付123
0451:加价处理中    有两种支付方式                           已支付  (微信支付)123        补价金额  (微信支付)123
0430:已取消       没有付款没有支付方式
0450:处理中       普通单 的处理中  有一种支付方式             订单总金额　(微信支付)123
0520:处理成功 -------------------------





0530:处理失败      有1中支付方式                       已支付      (微信支付)123

0531: 询价失败    有1中支付方式                     已支付      (微信支付)123

0610:退款中


0620: 退款完成


0630: 退款驳回:

0609: 退款审核中:


VIRTUAL("VIRTUAL"),//普通


ADDPRICE("ADDPRICE"),//加价

订单总金额(补价前)   ---------------已支付

如果: 0609 0620 0630  0610

if： TextUtils.isEmpty(carOrder.getAddPaydate()





DISPRICE("DISPRICE");//询价


询价单   处理中  ：

//orderdetail_payback
/********************************************/
//0413 报价待支付 需要添加一个还需支付  （未支付）
//0450 处理中   判断是询价订单  则表示是报价处理中   则需要添加一个还需支付   （微信支付）
0430:已取消   不用显示还需支付
//0520 处理成功
//0530  处理失败  则需要判断是否是询价订单  则代表两笔金额已经支付了   则还需要添加一个还需支付
//0610： 退款中
//0620： 退款完成
//0630:  退款驳回
//0609： 退款审核中
这几种 则需要判断是否是询价订单  则代表两笔金额已经支付   则还需要添加一个还需支付

/********************************************/


点击去支付的情况
0 == Net.getNameAuthentication()------弹窗去认证   跳转实名认证页面
1 ==Net.getNameAuthentication()---------跳转刷卡页面
2 ==Net.getNameAuthentication() 则：
if：  Y ：
        后台新字段判断是否是扣分单：
        是扣分单   -------跳转实名认证+机具认证页面
        不是扣分单 --------弹出互联网支付方式弹窗
else： N ：一定要机具认证

        弹出dialog 确认
        跳转实名认证+机具认证页面




     17620028867
     15116404484




--注册页面 发送验证码 返回的message 为null

1.修改了发送验证码、下一步点击按钮的时候的手机号11位判断
2.修改了发送短信验证码接口返回的onSuccess 的判断然后code="success"判断

--找回密码页面

1.修改了发送验证码、下一步点击按钮的时候的手机号11位判断
2.修改了发送短信验证码接口的返回类型 --对象  以及接口返回的onSuccess 的判断然后code判断

--登陆页面的快捷登录
1.修改了发送验证码、下一步点击按钮的时候的手机号11位判断



//弹窗的判断条件
//这里需要判断一下是否是2 还是0
                if(2== Net.getNameAuthentication()){
                    startActivity(new Intent(OrderDetailActivity.this, MyCAActivity.class));
                }else if(0== Net.getNameAuthentication()){
                    startActivity(new Intent(OrderDetailActivity.this, MyCaNoActivity.class));
                }



if(1==Net.getNameAuthentication()){
    //有机具认证过
    return;
}else if(0==Net.getNameAuthentication()){
    //没有实名认证和机具认证过
    //弹出dialog 点击确认之后跳转去实名认证页面


}else if(2==Net.getNameAuthentication()){

}







