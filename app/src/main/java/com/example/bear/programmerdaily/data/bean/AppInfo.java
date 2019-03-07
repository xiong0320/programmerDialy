package com.example.bear.programmerdaily.data.bean;

public class AppInfo {
    private String appName;
    private String appIcon;
    private int starCount;
    private int visitCount;
    private String appMainActivity;
    private String appPackget;

    public AppInfo(String appName, String appMainActivity) {
        this.appName = appName;
        this.appMainActivity = appMainActivity;
        this.starCount = 0;
        this.visitCount = 0;
        this.appPackget = "com.example.bear.programmerdaily";
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public String getAppMainActivity() {
        return appMainActivity;
    }

    public void setAppMainActivity(String appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    public String getAppPackget() {
        return appPackget;
    }

    public void setAppPackget(String appPackget) {
        this.appPackget = appPackget;
    }
}
