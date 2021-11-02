package com.tanvircodder.taskclander.model;

public class Event {
    String mName;
    String mLocation;
    String mCategory;
    String mDeadline;
    public Event() {
    }

    public Event(String mName, String mLocation, String mCategory, String mDeadline) {
        this.mName = mName;
        this.mLocation = mLocation;
        this.mCategory = mCategory;
        this.mDeadline = mDeadline;
    }

    public String getmName() {
        return mName;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmCategory() {
        return mCategory;
    }

    public String getmDeadline() {
        return mDeadline;
    }
}
