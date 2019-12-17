package com.willdom.luis.bottlerocket.api.interfaces;

import com.willdom.luis.bottlerocket.api.models.StoreResults;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BottleRocketApi {


    /**
     * Method to search the given phrase in the server database.
     *
     * @return SearchResult object containing the server response
     */
    @GET("/stores")
    Call<StoreResults> getStores();
}
