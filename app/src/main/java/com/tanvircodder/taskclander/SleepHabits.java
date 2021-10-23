package com.tanvircodder.taskclander;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tanvircodder.taskclander.model.Time;
import com.tanvircodder.taskclander.model.Time.*;

public class SleepHabits extends AppCompatActivity {
    private int stopMinute ;
    private int stopHour;
    private int stopSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_habits);
    }

    public Time defference(Time start, Time stop){
        Time diff = new Time(0,0,0);


    }
}