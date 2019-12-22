package com.willdom.luis.bottlerocket.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.willdom.luis.bottlerocket.BuildConfig;
import com.willdom.luis.bottlerocket.api.BottleRocketApiClient;
import com.willdom.luis.bottlerocket.api.interfaces.BottleRocketApi;
import com.willdom.luis.bottlerocket.api.models.ApiStoreModel;
import com.willdom.luis.bottlerocket.api.models.StoreResults;
import com.willdom.luis.bottlerocket.database.models.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class in charge to manage the data sources of the app.
 *
 * @author Luis Guzman
 */
public class StoreRepository {

    private static final String TAG = StoreRepository.class.getName();
    private static StoreRepository sInstance;
    private BottleRocketApi mApiClient;
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();

    /**
     * Private constructor for the Singleton pattern.
     */
    private StoreRepository() {
        mApiClient = BottleRocketApiClient.getInstance(BuildConfig.API_URL);
    }

    /**
     * Method for the Singleton design pattern implementation.
     *
     * @return StoreRepository instance
     */
    public static StoreRepository getInstance() {
        if(sInstance == null) {
            sInstance = new StoreRepository();
        }

        return sInstance;
    }

    /**
     *
     *
     * @return LiveData list of store objects
     */
//    public LiveData<List<ApiStoreModel>> getStores() {
    public MutableLiveData<List<ApiStoreModel>> getStores() {

        mIsLoading.setValue(true);
        final MutableLiveData<List<ApiStoreModel>> storesData = new MutableLiveData<>();
        AtomicBoolean isDatabaseEmpty = new AtomicBoolean(false);

        try(Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction((Realm realm) -> {
                RealmResults<Store> stores = realm.where(Store.class).findAll();

                if(stores.isEmpty()) {
                    isDatabaseEmpty.set(true);
                } else {

                    ArrayList<ApiStoreModel> newStores = new ArrayList<>();
                    for (Store databaseStore: stores) {
                        ApiStoreModel newStore = new ApiStoreModel(databaseStore);
                        newStores.add(newStore);
                    }

                    storesData.setValue(newStores);
                    mIsLoading.setValue(false);
                }
            });
        }

        // We can also add another validation in order to make the call
        if(isDatabaseEmpty.get()) {

            mApiClient.getStores().enqueue(new Callback<StoreResults>() {

                @Override
                public void onResponse(Call<StoreResults> call, Response<StoreResults> response) {
                    Log.i(TAG, "onResponse: List size " + response.body().getStores().size());
                    storesData.postValue(response.body().getStores());
                    addNewStore(response.body().getStores());
                    mIsLoading.postValue(false);
                }

                @Override
                public void onFailure(Call<StoreResults> call, Throwable error) {
                    Log.e(TAG, "Request failed while communicating with server: ", error);
                    storesData.postValue(null);
                    mIsLoading.postValue(false);
                }
            });
        }

        return storesData;
    }

    /**
     * Method in charge to save the API returned stores to the database.
     *
     * @param stores List of store
     */
    private void addNewStore(List<ApiStoreModel> stores) {

        try(Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction((Realm realm) -> {

                for (ApiStoreModel apiModel : stores) {
                    Store newStore = new Store(apiModel);

                    realm.insert(newStore);
                }
            });
        }
    }

    /**
     *
     * @return
     */
    public MutableLiveData<Boolean> isLoading() {
        return this.mIsLoading;
    }
}
