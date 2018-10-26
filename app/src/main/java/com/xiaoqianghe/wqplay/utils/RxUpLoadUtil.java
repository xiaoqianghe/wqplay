package com.xiaoqianghe.wqplay.utils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author：Wq
 * Date：2018/10/17 15:59
 * Description：//todo 上传的工具类
 */

public class RxUpLoadUtil {

    //@TODO 创建 requestBody
    public static MultipartBody.Part createRequestBody(String path){
        File file= new File(path);
        RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpg"),file);
        MultipartBody.Part body= MultipartBody.Part.createFormData("picture",file.getName(),requestBody);
        return body;
    }


    //





}
