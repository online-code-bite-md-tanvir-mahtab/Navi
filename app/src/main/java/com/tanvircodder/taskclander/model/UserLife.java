package com.tanvircodder.taskclander.model;

public class UserLife {
    private String mCareer;
    private String mFamily;
    private String mFun;
    private String mFitness;
    private String mAdd;
    private String mCareer_hour,mFamily_hour,mFun_hour,mFitness_hour,mAdd_hour;


    public UserLife(String mCareer, String mFamily, String mFun,
                    String mFitness, String mAdd, String mCareer_hour,
                    String mFamily_hour, String mFun_hour,
                    String mFitness_hour, String mAdd_hour) {
        this.mCareer = mCareer;
        this.mFamily = mFamily;
        this.mFun = mFun;
        this.mFitness = mFitness;
        this.mAdd = mAdd;
        this.mCareer_hour = mCareer_hour;
        this.mFamily_hour = mFamily_hour;
        this.mFun_hour = mFun_hour;
        this.mFitness_hour = mFitness_hour;
        this.mAdd_hour = mAdd_hour;
    }

    public String getmCareer() {
        return mCareer;
    }

    public String getmFamily() {
        return mFamily;
    }

    public String getmFun() {
        return mFun;
    }

    public String getmFitness() {
        return mFitness;
    }

    public String getmAdd() {
        return mAdd;
    }

    public String getmCareer_hour() {
        return mCareer_hour;
    }

    public String getmFamily_hour() {
        return mFamily_hour;
    }

    public String getmFun_hour() {
        return mFun_hour;
    }

    public String getmFitness_hour() {
        return mFitness_hour;
    }

    public String getmAdd_hour() {
        return mAdd_hour;
    }
}
