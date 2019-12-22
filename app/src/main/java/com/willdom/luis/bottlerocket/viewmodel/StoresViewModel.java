package com.willdom.luis.bottlerocket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.willdom.luis.bottlerocket.api.models.ApiStoreModel;
import com.willdom.luis.bottlerocket.repositories.StoreRepository;

import java.util.List;

/**
 * @author Luis Guzman
 */
public class StoresViewModel extends AndroidViewModel {

    private MutableLiveData<List<ApiStoreModel>> mStores;
    private StoreRepository mStoreRepository;



    public StoresViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     *
     */
    public void init() {
        if(mStoreRepository != null) {
            return;
        }

        mStoreRepository = StoreRepository.getInstance();
        mStores = mStoreRepository.getStores();
//        mStores.setValue(mStoreRepository.getStores().getValue());
    }

    /**
     *
     * @return
     */
    public LiveData<List<ApiStoreModel>> getStores() {
        return mStores;
    }

    public LiveData<Boolean> isLoading() {
        return mStoreRepository.isLoading();
    }
}
