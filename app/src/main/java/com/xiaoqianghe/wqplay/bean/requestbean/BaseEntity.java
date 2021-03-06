package com.xiaoqianghe.wqplay.bean.requestbean;

import java.io.Serializable;
import java.util.List;

/**
 * Author：Wq
 * Date：2017/8/18 14:42
 * Description：//todo
 */

public class BaseEntity implements Serializable {

    private boolean hasMore;
    private String host;
    private String thumbnail;
    private List<AppInfo> listApp;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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
