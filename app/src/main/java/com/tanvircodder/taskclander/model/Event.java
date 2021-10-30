package com.tanvircodder.taskclander.model;

public class Event {
    private String mName;
    private String mLocation;

    public String getmName() {
        return mName;
    }

    public String getmLocation() {
        return mLocation;
    }

    public Event(String mName, String mLocation) {
        this.mName = mName;
        this.mLocation = mLocation;
    }
}
