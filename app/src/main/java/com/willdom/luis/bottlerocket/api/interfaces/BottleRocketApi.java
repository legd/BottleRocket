package com.willdom.luis.bottlerocket.api.interfaces;

import com.willdom.luis.bottlerocket.api.models.StoreResults;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface to define the BottleRocket API.
 *
 * @author Luis Guzman
 */
public interface BottleRocketApi {

    /**
     * Method to get all the stores.
     *
     * @return StoreResults object containing a list of stores
     */
    @GET("stores.json")
    Call<StoreResults> getStores();
}
