package com.loft.shareutil;

public class ShareVo {
    private String title;
    private String summary;
    private String url;
    private String imgUrl;
    private String appName;
    private int ext;

    public ShareVo(String title, String summary, String url, String imgUrl, String appName, int ext) {
        this.title = title;
        this.summary = summary;
        this.url = url;
        this.imgUrl = imgUrl;
        this.appName = appName;
        this.ext = ext;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getExt() {
        return ext;
    }

    public void setExt(int ext) {
        this.ext = ext;
    }
}
