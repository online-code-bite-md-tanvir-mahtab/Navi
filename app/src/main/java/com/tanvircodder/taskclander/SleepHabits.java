package com.tanvircodder.taskclander;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tanvircodder.taskclander.model.Time;
import com.tanvircodder.taskclander.model.Time.*;

import java.time.Duration;
import java.time.LocalTime;

public class SleepHabits extends AppCompatActivity {

    private EditText editStartHour,editEndHour;
    private TextView mHourVieww;
    private EditText mPreSleep,mPostSleep;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_habits);
        calculateTime();
    }

    private void calculateTime(){
        editStartHour = (EditText) findViewById(R.id.start_hour);
        editEndHour = (EditText) findViewById(R.id.end_hour);
        mHourVieww = (TextView) findViewById(R.id.calculated_hour);
        mPreSleep = (EditText) findViewById(R.id.pree_time);
        mPostSleep = (EditText) findViewById(R.id.post_time);

        String start = editStartHour.getText().toString();
        String stop  = editEndHour.getText().toString();
        long total = timeCalculator(start,stop);
        mHourVieww.setText(Long.toString(total));
        Time time = new Time(start,stop,total);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(Time.class.getSimpleName());
        myRef.setValue(time);
    }

    public long timeCalculator(String start,String stop){
        long total = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime start_hour = LocalTime.parse(start) ;
            LocalTime stop_hour = LocalTime.parse( stop ) ;

            Duration duration = Duration.between(start_hour,stop_hour);
            total = duration.getSeconds()/3600;


        }
        return total;
    }
}

