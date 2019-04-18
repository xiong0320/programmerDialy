package com.example.bear.programmerdaily.data.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;


import com.example.bear.programmerdaily.data.db.AppInfoEntity;
import com.example.bear.programmerdaily.data.repository.AppInfoRepository;

import java.util.List;

public class AppInfoViewModel extends AndroidViewModel {
    private AppInfoRepository mRepository;
    private LiveData<List<AppInfoEntity>> mAllAppInfos;
    public AppInfoViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AppInfoRepository(application);
        mAllAppInfos = mRepository.getAllAppInfo();
    }
    public LiveData<List<AppInfoEntity>> getAllAppInfos() { return mAllAppInfos; }
    public void insert(AppInfoEntity appInfoEntity) {
        mRepository.insert(appInfoEntity);
    }
}
