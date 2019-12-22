package com.willdom.luis.bottlerocket.database.models;

import com.willdom.luis.bottlerocket.api.models.ApiStoreModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Class to represent the table model for a Store.
 *
 * @author Luis Guzman
 */
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
    //                  CONSTRUCTOR
    //======================================================

    public Store() {
    }

    public Store(ApiStoreModel apiStore) {

        this.mAddress = apiStore.getAddress();
        this.mCity = apiStore.getCity();
        this.mName = apiStore.getName();
        this.mLatitude = apiStore.getLatitude();
        this.mZipcode = apiStore.getZipcode();
        this.mStoreLogoUrl = apiStore.getStoreLogoUrl();
        this.mPhone = apiStore.getPhone();
        this.mLongitude = apiStore.getLongitude();
        this.mStoreId = apiStore.getStoreId();
        this.mState = apiStore.getState();
    }

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
