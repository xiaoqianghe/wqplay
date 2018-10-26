package com.xiaoqianghe.wqplay.common.http;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.xiaoqianghe.wqplay.common.Constant;
import com.xiaoqianghe.wqplay.common.util.DensityUtil;
import com.xiaoqianghe.wqplay.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Author：Wq
 * Date：2017/6/20 15:32
 * Description：//todo
 */

public class CommonParamsInterceptor implements Interceptor {

    private final String TAG = this.getClass().getSimpleName();


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    private Gson mGson;
    private Context mContext;


    public CommonParamsInterceptor(Context mContext, Gson mGson) {

        Log.d(TAG,"CommonParamsInterceptor 构造函数");
        this.mContext = mContext;
        this.mGson = mGson;
    }


    public CommonParamsInterceptor(Gson mGson) {

        Log.d(TAG,"CommonParamsInterceptor 构造函数");
        this.mGson = mGson;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();


        try {

        String method=request.method();
        HashMap<String,Object> commomParamsMap=new HashMap<String,Object>();
        commomParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
//            commomParamsMap.put(Constant.IMEI, "");

        commomParamsMap.put(Constant.MODEL,DeviceUtils.getModel());
        commomParamsMap.put(Constant.LANGUAGE,DeviceUtils.getLanguage());
        commomParamsMap.put(Constant.os,DeviceUtils.getBuildVersionIncremental());
        commomParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext)+"*"+DensityUtil.getScreenH(mContext));
//            commomParamsMap.put(Constant.RESOLUTION, "");

        commomParamsMap.put(Constant.SDK,DeviceUtils.getBuildVersionSDK());
        commomParamsMap.put(Constant.DENSITY_SCALE_FACTOR,mContext.getResources().getDisplayMetrics().density+"");

        Log.d(TAG,"CommonParamsInterceptor =======intercept");
        if(method.equals("GET")){

           HttpUrl httpUrl= request.url();

            HashMap<String,Object> rootMap=new HashMap<String,Object>();

            Set<String> paramNames=httpUrl.queryParameterNames();

            for(String key:paramNames){
                if(Constant.PARAM.equals(key)){

                   String oldParamsJson= httpUrl.queryParameter(Constant.PARAM);
                    if(oldParamsJson!=null){
                      HashMap<String,Object> p= mGson.fromJson(oldParamsJson,HashMap.class);
                        if(null!=p){
                            for(Map.Entry<String,Object> entity:p.entrySet()){
                                rootMap.put(entity.getKey(),entity.getValue());
                            }
                        }
                    }
                }else{
                    rootMap.put(key,httpUrl.queryParameter(key));
                }
            }


            rootMap.put("publicParams",commomParamsMap);
            String newJsonParams=mGson.toJson(rootMap);//得到Get请求传递的参数

            String url=httpUrl.toString();

            int index= url.indexOf("?");
            if(index>0){
                url =url.substring(0,index);
            }
            url=url+"?"+Constant.PARAM+"="+newJsonParams;


            Log.d(TAG,"url::"+url);

            request=request.newBuilder().url(url).build();

        }else if(method.equals("POST")){

            RequestBody body=request.body();

            HashMap<String,Object> rootMap = new HashMap<>();
            if(body instanceof FormBody){ // form 表单

                for (int i=0;i<((FormBody) body).size();i++){

                    rootMap.put(((FormBody) body).encodedName(i),((FormBody) body).encodedValue(i));
                }

            }
            else{

                Buffer buffer = new Buffer();

                body.writeTo(buffer);

                String oldJsonParams =  buffer.readUtf8();

                rootMap = mGson.fromJson(oldJsonParams,HashMap.class); // 原始参数
                rootMap.put("publicParams",commomParamsMap); // 重新组装
                String newJsonParams = mGson.toJson(rootMap); // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}
                request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();


            }

        }

        } catch (JsonSyntaxException e) {


            Log.d(TAG,"CommonParamsInterceptor =======JsonSyntaxException e：："+e.toString());
            e.printStackTrace();
        }

        //@todo 这里可以重新拦截      添加 Token



        return chain.proceed(request);
    }
}
