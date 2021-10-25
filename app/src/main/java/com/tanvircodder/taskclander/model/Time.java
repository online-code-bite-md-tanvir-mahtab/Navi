package com.tanvircodder.taskclander.model;

public class Time {

    private String mStartHour;
    private String mStopHour;
    private long mTotal;

    public Time(String mStartHour, String mStopHour,long mTotal) {
        this.mStartHour = mStartHour;
        this.mStopHour = mStopHour;
        this.mTotal = mTotal;
    }

    public String getmStartHour() {
        return mStartHour;
    }

    public String getmStopHour() {
        return mStopHour;
    }

    public long getmTotal() {
        return mTotal;
    }
}
