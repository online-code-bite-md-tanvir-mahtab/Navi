package com.tanvircodder.taskclander.model;

public class User {
    private String mFName;
    private String mFamillyName;
    private String mEmail;
    private String mBirth;
    private String mCountry;
    private String mGender;

    public User(String mFName, String mFamillyName, String mEmail,
                String mBirth, String mCountry, String mGender) {
        this.mFName = mFName;
        this.mFamillyName = mFamillyName;
        this.mEmail = mEmail;
        this.mBirth = mBirth;
        this.mCountry = mCountry;
        this.mGender = mGender;
    }

    public String getmFName() {
        return mFName;
    }

    public String getmFamillyName() {
        return mFamillyName;
    }
}
