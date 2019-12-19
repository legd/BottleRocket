package com.willdom.luis.bottlerocket.models;

/**
 * Created by luis on 2019-12-19
 */
public class Store {

    private String mAddress;

    private String mCity;

    private String mName;

    private String mLatitude;

    private String mZipcode;

    private String mStoreLogoUrl;

    private String mPhone;

    private String mLongitude;

    private String mStoreId;

    private String mState;

    //===========================================================================
    //                         CONSTRUCTORS
    //===========================================================================

    public Store(String address, String city, String name, String latitude, String zipcode,
                 String storeLogoUrl, String phone, String longitude, String storeId, String state) {

        this.mAddress = address;
        this.mCity = city;
        this.mName = name;
        this.mLatitude = latitude;
        this.mZipcode = zipcode;
        this.mStoreLogoUrl = storeLogoUrl;
        this.mPhone = phone;
        this.mLongitude = longitude;
        this.mStoreId = storeId;
        this.mState = state;
    }

    public Store() {
    }

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
