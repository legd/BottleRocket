package com.willdom.luis.bottlerocket.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Class to represent the API model for a store list response.
 *
 * @author Luis Guzman
 */
public class StoreResults {

    @SerializedName("stores")
    @Expose
    private List<Store> mStores;

    //===========================================================================
    //                         GETTERS AND SETTERS
    //===========================================================================

    public List<Store> getStores() {
        return mStores;
    }

    public void setStores(List<Store> stores) {
        this.mStores = stores;
    }
}
