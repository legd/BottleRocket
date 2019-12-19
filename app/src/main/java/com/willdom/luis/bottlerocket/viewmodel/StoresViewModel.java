package com.willdom.luis.bottlerocket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.willdom.luis.bottlerocket.models.Store;
import com.willdom.luis.bottlerocket.repositories.StoreRepository;

import java.util.List;

/**
 * Created by luis on 2019-12-19
 */
public class StoresViewModel extends AndroidViewModel {

    private MutableLiveData<List<Store>> mStores;
    private StoreRepository mStoreRepository;

    public StoresViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        if(mStoreRepository != null) {
            return;
        }

        mStoreRepository = StoreRepository.getInstance();
        mStores = mStoreRepository.getStores();
    }

    public LiveData<List<Store>> getStores() {
        return mStores;
    }
}
