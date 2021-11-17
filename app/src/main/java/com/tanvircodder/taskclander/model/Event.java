package com.tanvircodder.taskclander.model;

public class Event {
    String mName;
    String mLocation;
    String mCategory;
    String mDeadline;
    int mColor;

    public Event(String mName, String mLocation, String mCategory, String mDeadline, int mColor) {
        this.mName = mName;
        this.mLocation = mLocation;
        this.mCategory = mCategory;
        this.mDeadline = mDeadline;
        this.mColor = mColor;
    }

    public Event() {
    }

    public Event(String mName, String mLocation) {
        this.mName = mName;
        this.mLocation = mLocation;
    }



    public String getmCategory() {
        return mCategory;
    }

    public String getmDeadline() {
        return mDeadline;
    }

    public int getmColor() {
        return mColor;
    }

    public String getmName() {
        return mName;
    }

    public String getmLocation() {
        return mLocation;
    }
}
