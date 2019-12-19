package com.willdom.luis.bottlerocket.repositories;

import androidx.lifecycle.MutableLiveData;

import com.willdom.luis.bottlerocket.models.Store;

import java.util.List;

/**
 * Created by luis on 2019-12-19
 */
public class StoreRepository {

    // mankejar acceso a data aqui
    // singleton
    private static StoreRepository sInstance;

    private StoreRepository() {
    }

    public static StoreRepository getInstance() {
        if(sInstance == null) {
            sInstance = new StoreRepository();
        }

        return sInstance;
    }

    public MutableLiveData<List<Store>> getStores() {
        MutableLiveData<List<Store>> storesData = new MutableLiveData<>();
//        storesData.setValue();
        return storesData;
    }
}
