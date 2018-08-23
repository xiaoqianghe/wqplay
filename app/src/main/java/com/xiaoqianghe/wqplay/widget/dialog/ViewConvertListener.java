package com.xiaoqianghe.wqplay.widget.dialog;

import java.io.Serializable;

/**
 * Created by ixzus on 2018/1/3.
 * Email: iandroid@foxmail.com
 * Desc:
 */

public interface ViewConvertListener extends Serializable {
    long serialVersionUID = System.currentTimeMillis();

    void convertView(ViewHolder holder, AbsDialog dialog);
}