package com.example.bear.programmerdaily.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.bear.programmerdaily.data.dao.AppInfoDao;
import com.example.bear.programmerdaily.data.db.AppInfoEntity;
import com.example.bear.programmerdaily.data.db.AppInfoRoomDatabase;

import java.util.List;

public class AppInfoRepository {
    private AppInfoDao mAppInfoDao;
    private LiveData<List<AppInfoEntity>> mAllAppInfo;

    public AppInfoRepository(Application application) {
        AppInfoRoomDatabase database = AppInfoRoomDatabase.getDatabase(application);
        mAppInfoDao = database.appInfoDao();
        mAllAppInfo = mAppInfoDao.getAllAppInfoByName();
    }
    public LiveData<List<AppInfoEntity>> getAllAppInfo() {
        return mAllAppInfo;
    }
    public void insert (AppInfoEntity appInfoEntity) {
        new insertAsyncTask(mAppInfoDao).execute(appInfoEntity);
    }



    private static class insertAsyncTask extends AsyncTask<AppInfoEntity,Void,Void> {
        private AppInfoDao mAsyncTaskDao;
        insertAsyncTask(AppInfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AppInfoEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
