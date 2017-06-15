package com.xiaoqianghe.wqplay.bean.requestbean;

import java.io.Serializable;

/**
 * Author：Wq
 * Date：2017/6/15 21:31
 * Description：//todo
 */

public class BaseBean<T> implements Serializable {


    private static final int SUCCESS=1;


    private int status;
    private  String message;
    private T data;

    public boolean SUCCESS(){

        return (status==SUCCESS);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
