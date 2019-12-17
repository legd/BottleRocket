package com.willdom.luis.bottlerocket.database.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Store extends RealmObject {

    @PrimaryKey
    private String mStoreId;
    private String mAddress;
    private String mCity;
    private String mName;
    private String mLatitude;
    private String mZipcode;
    private String mStoreLogoUrl;
    private String mPhone;
    private String mLongitude;
    private String mState;

    //======================================================
    //                  GETTERS AND SETTERS
    //======================================================


    public String getStoreId() {
        return mStoreId;
    }

    public void setStoreId(String storeId) {
        this.mStoreId = storeId;
    }

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

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        this.mState = state;
    }
}
