package com.tanvircodder.taskclander.model;

public class Event {
    String mName;
    String mLocation;

    public Event() {
    }

    public Event(String mName, String mLocation) {
        this.mName = mName;
        this.mLocation = mLocation;
    }
    public String getmName() {
        return mName;
    }

    public String getmLocation() {
        return mLocation;
    }


}
