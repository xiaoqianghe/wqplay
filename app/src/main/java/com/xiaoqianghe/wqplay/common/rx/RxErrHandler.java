package com.xiaoqianghe.wqplay.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.xiaoqianghe.wqplay.common.exception.ApiException;
import com.xiaoqianghe.wqplay.common.exception.BaseException;
import com.xiaoqianghe.wqplay.common.exception.ErrorMessageFactory;

import org.json.JSONException;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Author：Wq
 * Date：2017/6/16 16:32
 * Description：//todo
 */

public class RxErrHandler {

    private Context mContext;

    public RxErrHandler(Context mContext) {
        this.mContext = mContext;
    }


    public BaseException handleError(Throwable e){
        BaseException exception=new BaseException();
        if(e instanceof ApiException){
            exception.setCode(((ApiException) e).getCode());
        }
        else if(e instanceof JsonParseException){

            exception.setCode(BaseException.JSON_ERROR);

        }
        else if(e instanceof HttpException){
            exception.setCode(((HttpException) e).code());
        }else if(e instanceof SocketTimeoutException){
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        }
        else if(e instanceof SocketException){

        }else {
            exception.setCode(BaseException.UNKNOWN_ERROR);

        }

        exception.setDisplayMessage(ErrorMessageFactory.create(mContext,exception.getCode()));


        return exception;

    }


    public void  showErrorMessage(BaseException e){


        Toast.makeText(mContext,e.getDisplayMessage(),Toast.LENGTH_LONG).show();

    }
}
