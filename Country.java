package com.mohan.myapplication;

public class Country {

    private String mCountryName;
    private String mCountryCode;
    private String mCountryId;

     Country(String countryName, String countryCode, String countryId) {
        this.mCountryName = countryName;
        this.mCountryCode = countryCode;
        this.mCountryId = countryId;

    }

    public String getCountryName() {
        return mCountryName;
    }

    public void setCountryName(String countryName) {
        mCountryName = countryName;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public String getCountryId() {
        return mCountryId;
    }

    public void setCountryId(String countryId) {
        mCountryId = countryId;
    }
}
