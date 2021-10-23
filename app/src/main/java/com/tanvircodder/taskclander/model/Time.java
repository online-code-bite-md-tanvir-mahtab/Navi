package com.tanvircodder.taskclander.model;

public class Time {

    private int mHour;
    private int mMiniue;
    private int mSecond;

    public Time(int mHour, int mMiniue, int mSecond) {
        this.mHour = mHour;
        this.mMiniue = mMiniue;
        this.mSecond = mSecond;
    }

    public int getmHour() {
        return mHour;
    }

    public int getmMiniue() {
        return mMiniue;
    }

    public int getmSecond() {
        return mSecond;
    }
}
