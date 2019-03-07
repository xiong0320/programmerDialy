package com.example.bear.programmerdaily.data.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "app_info_table")
public class AppInfoEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String appName;
    private String keyWord;
    private String appPackage;
    private String appClassName;
    private String appImgUrl;
    private String appInfoDetail;
    private String appDeveloper;
    @NonNull
    private int appDeveloperId;
    private int appStar;
    private int appVisitCount;
    private int appDiscussCount;
    private int appDiscussId;
    private String appType;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppClassName() {
        return appClassName;
    }

    public void setAppClassName(String appClassName) {
        this.appClassName = appClassName;
    }

    public String getAppImgUrl() {
        return appImgUrl;
    }

    public void setAppImgUrl(String appImgUrl) {
        this.appImgUrl = appImgUrl;
    }

    public String getAppInfoDetail() {
        return appInfoDetail;
    }

    public void setAppInfoDetail(String appInfoDetail) {
        this.appInfoDetail = appInfoDetail;
    }

    public String getAppDeveloper() {
        return appDeveloper;
    }

    public void setAppDeveloper(String appDeveloper) {
        this.appDeveloper = appDeveloper;
    }

    public int getAppDeveloperId() {
        return appDeveloperId;
    }

    public void setAppDeveloperId(int appDeveloperId) {
        this.appDeveloperId = appDeveloperId;
    }

    public int getAppStar() {
        return appStar;
    }

    public void setAppStar(int appStar) {
        this.appStar = appStar;
    }

    public int getAppVisitCount() {
        return appVisitCount;
    }

    public void setAppVisitCount(int appVisitCount) {
        this.appVisitCount = appVisitCount;
    }

    public int getAppDiscussCount() {
        return appDiscussCount;
    }

    public void setAppDiscussCount(int appDiscussCount) {
        this.appDiscussCount = appDiscussCount;
    }

    public int getAppDiscussId() {
        return appDiscussId;
    }

    public void setAppDiscussId(int appDiscussId) {
        this.appDiscussId = appDiscussId;
    }
}
