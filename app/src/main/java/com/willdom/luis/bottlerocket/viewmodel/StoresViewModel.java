package com.willdom.luis.bottlerocket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.willdom.luis.bottlerocket.api.models.ApiStoreModel;
import com.willdom.luis.bottlerocket.repositories.StoreRepository;
import com.willdom.luis.bottlerocket.repositories.StoreRepositoryState;

import java.util.List;

/**
 * Class to represent the stores ViewModel in the MVVM pattern.
 *
 * @author Luis Guzman
 */
public class StoresViewModel extends AndroidViewModel {

    private MutableLiveData<List<ApiStoreModel>> mStores;
    private StoreRepository mStoreRepository;

    //===========================================================================
    //                           CONSTRUCTOR
    //===========================================================================

    public StoresViewModel(@NonNull Application application) {
        super(application);
    }


    //===========================================================================
    //                           CUSTOM METHODS
    //===========================================================================
    /**
     * Method in charge of the initialization of the repository class and store list.
     */
    public void init() {

        if(mStoreRepository != null) {
            return;
        }

        mStoreRepository = StoreRepository.getInstance();
        mStores = mStoreRepository.getStores();
    }

    /**
     * Method for returning the stores livedata list from th repository.
     *
     * @return LiveData object of type List containing the stores
     */
    public LiveData<List<ApiStoreModel>> getStores() {
        return mStores;
    }

    /**
     * Method for returning the loading status livedata from the repository.
     *
     * @return LiveData object of type StoreRepositoryState object containing the loading status info
     */
    public LiveData<StoreRepositoryState> isLoading() {
        return mStoreRepository.isLoading();
    }
}
