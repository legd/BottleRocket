package com.willdom.luis.bottlerocket.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("address")
    @Expose
    private String mAddress;

    @SerializedName("city")
    @Expose
    private String mCity;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("latitude")
    @Expose
    private String mLatitude;

    @SerializedName("zipcode")
    @Expose
    private String mZipcode;

    @SerializedName("storeLogoURL")
    @Expose
    private String mStoreLogoUrl;

    @SerializedName("phone")
    @Expose
    private String mPhone;

    @SerializedName("longitude")
    @Expose
    private String mLongitude;

    @SerializedName("storeID")
    @Expose
    private String mStoreId;

    @SerializedName("state")
    @Expose
    private String mState;

    //===========================================================================
    //                         GETTERS AND SETTERS
    //===========================================================================

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        this.mLatitude = latitude;
    }

    public String getZipcode() {
        return mZipcode;
    }

    public void setZipcode(String zipcode) {
        this.mZipcode = zipcode;
    }

    public String getStoreLogoUrl() {
        return mStoreLogoUrl;
    }

    public void setStoreLogoUrl(String storeLogoUrl) {
        this.mStoreLogoUrl = storeLogoUrl;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        this.mPhone = phone;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        this.mLongitude = longitude;
    }

    public String getStoreId() {
        return mStoreId;
    }

    public void setStoreId(String storeId) {
        this.mStoreId = storeId;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        this.mState = state;
    }
}
