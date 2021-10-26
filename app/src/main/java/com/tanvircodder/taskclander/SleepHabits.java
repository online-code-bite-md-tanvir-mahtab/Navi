package com.tanvircodder.taskclander;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tanvircodder.taskclander.model.Time;
import com.tanvircodder.taskclander.model.Time.*;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SleepHabits extends AppCompatActivity {

    public  static final String LOG_TAG = SleepHabits.class.getSimpleName();
    private EditText editStartHour,editEndHour;
    private TextView mHourVieww;
    private EditText mPreSleep,mPostSleep;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_habits);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public  void calculate(View view){
        editStartHour = (EditText) findViewById(R.id.start_hour);
        editEndHour = (EditText) findViewById(R.id.end_hour);

        mPreSleep = (EditText) findViewById(R.id.pree_time);
        mPostSleep = (EditText) findViewById(R.id.post_time);

        String start = editStartHour.getText().toString();
        String stop  = editEndHour.getText().toString();
        System.out.println(start +"\n" + stop);
        mHourVieww = (TextView) findViewById(R.id.calculated_hour);
        long total = 0;
        LocalTime start_hour = LocalTime.parse(start) ;
        LocalTime stop_hour = LocalTime.parse( stop) ;

        Duration duration = Duration.between(start_hour,stop_hour);
        total = duration.getSeconds()/3600;
        Time time = new Time(start,stop,total);
        mHourVieww.setText(Long.toString(total));
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(Time.class.getSimpleName());
        myRef.setValue(time);

    }
}

