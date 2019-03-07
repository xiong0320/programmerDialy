package com.example.bear.programmerdaily.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.bear.programmerdaily.data.dao.AppInfoDao;

@Database(entities = {AppInfoEntity.class},version = 1)
public abstract class AppInfoRoomDatabase extends RoomDatabase {
    public abstract AppInfoDao appInfoDao();
    private static volatile AppInfoRoomDatabase INSTANCE;
    public static AppInfoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppInfoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppInfoRoomDatabase.class,"app_info_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
