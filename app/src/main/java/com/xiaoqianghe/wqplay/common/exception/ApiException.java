package com.xiaoqianghe.wqplay.common.exception;

/**
 * Author：Wq
 * Date：2017/6/16 14:59
 * Description：//todo
 */

public class ApiException extends BaseException {

    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
