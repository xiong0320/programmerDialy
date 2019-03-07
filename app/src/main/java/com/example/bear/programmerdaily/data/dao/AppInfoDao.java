package com.example.bear.programmerdaily.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.bear.programmerdaily.data.db.AppInfoEntity;

import java.util.List;

@Dao
public interface AppInfoDao {
    @Insert
    void insert(AppInfoEntity appInfoEntity);
    @Query("DELETE FROM app_info_table")
    void deleteAll();

    @Query("SELECT * from app_info_table ORDER BY appName ASC")
    LiveData<List<AppInfoEntity>> getAllAppInfoByName();
    @Query("SELECT * from app_info_table ORDER BY appStar DESC")
    List<AppInfoEntity> getAllAppInfoByStar();
    @Query("SELECT * from app_info_table ORDER BY appVisitCount DESC")
    List<AppInfoEntity> getAllAppInfoByVisit();
    @Query("SELECT * from app_info_table ORDER BY appDiscussCount DESC")
    List<AppInfoEntity> getAllAppInfoByDiscuss();
}
