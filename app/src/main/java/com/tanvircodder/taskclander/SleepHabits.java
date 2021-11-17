package com.tanvircodder.taskclander;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class SleepHabits extends AppCompatActivity {

    public  static final String LOG_TAG = SleepHabits.class.getSimpleName();
    private EditText editStartHour,editEndHour;
    private TextView mHourVieww;
    private EditText mPreSleep,mPostSleep;
    private int mHour,mMinute;
    private int mHour2,mMinute2;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_habits);
        editStartHour = (EditText) findViewById(R.id.start_hour);
        editEndHour = (EditText) findViewById(R.id.end_hour);

        editStartHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                defining the object of the Calendar class.
                final Calendar calendar = Calendar.getInstance();

//                geting the day hour from thecalender and store it in the first hour..
                mHour = calendar.get(Calendar.HOUR_OF_DAY);
//                getting the minute of the day and store them in the
                mMinute = calendar.get(Calendar.MINUTE);

//                for he timepicker layout
                TimePickerDialog timePickerDialog = new TimePickerDialog(SleepHabits.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        now i am goig ot se the
                        editStartHour.setText(hourOfDay + ":" + minute);
                    }
                },mHour,mMinute,false);
                timePickerDialog.show();
            }
        });
        editEndHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                mHour2 = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute2 = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(SleepHabits.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        editEndHour.setText(hourOfDay + ":" + minute);
                    }
                },mHour2,mMinute2,false);
                timePickerDialog.show();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String[] firstHour = editStartHour.getText().toString().split(":");
                String[] secondHour = editEndHour.getText().toString().split(":");
                double f_hour = convertToFirstHour(firstHour);
                double s_hour = convertToSecondHour(secondHour);
                String totalHour = Integer.toString((int) ((f_hour-s_hour)/3660));
                mHourVieww.setText(totalHour);
            }
        });
    }
    public void calculate(View view){


        mPreSleep = (EditText) findViewById(R.id.pree_time);
        mPostSleep = (EditText) findViewById(R.id.post_time);
        editStartHour = (EditText) findViewById(R.id.start_hour);
        editEndHour = (EditText) findViewById(R.id.end_hour);
        mHourVieww = (TextView) findViewById(R.id.calculated_hour);

        if (editStartHour.getText()!=null && editEndHour.getText() != null && mPreSleep.getText() != null && mPostSleep.getText() != null){
            Intent intent = new Intent(SleepHabits.this,LifeArea.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Please fill up the box",Toast.LENGTH_LONG)
                    .show();
        }


    }
    private double convertToFirstHour(String[] firstHour) {
        double  hour =0;
        double  minute =0 ;
        if (firstHour.length > 0){
            hour = Integer.parseInt(firstHour[0]);
            minute = Integer.parseInt(firstHour[1]);
            return (hour*3600)+(minute*60);
        }else{

        }
        return (hour*3600)+(minute*60);

    }
    private double convertToSecondHour(String[] firstHour) {
        double hour = 0;
        double minute = 0;
        if (firstHour.length > 0){
            hour = Integer.parseInt(firstHour[0]);
            minute = Integer.parseInt(firstHour[1]);
            System.out.println(hour + ":" + minute);
        }else{

        }
        return (hour*3600)+(minute*60);
    }
}

