package com.xiaoqianghe.wqplay.bean;

import com.xiaoqianghe.wqplay.bean.requestbean.AppInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Author：Wq
 * Date：2017/12/11 17:30
 * Description：//todo
 */

public class AppInfoPager implements Serializable{

    private boolean hasMore;
    private String host;
    private String thumbnail;
    private List<AppInfo> listApp;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<AppInfo> getListApp() {
        return listApp;
    }

    public void setListApp(List<AppInfo> listApp) {
        this.listApp = listApp;
    }
}
