package com.tanvircodder.taskclander;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.rtugeek.android.colorseekbar.ColorSeekBar;
import com.tanvircodder.taskclander.model.Event;
import com.tanvircodder.taskclander.model.Time;
import com.warkiz.tickseekbar.OnSeekChangeListener;
import com.warkiz.tickseekbar.SeekParams;
import com.warkiz.tickseekbar.TickSeekBar;

public class AddActivity extends AppCompatActivity {
    private static final String LOG_TAG  = AddActivity.class.getSimpleName();

    private EditText mNameOfEvent;
    private EditText mNameOfLocation;
    private EditText mCategoryEvent;
    private EditText mDadeLine;
    private Button mButton;
    private DatabaseReference mDatabase;
    private ColorSeekBar colorSeekBar;
    private TickSeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Add Event");

        mNameOfEvent =(EditText) findViewById(R.id.event_name);
        mNameOfLocation = (EditText) findViewById(R.id.where);
        mCategoryEvent = (EditText) findViewById(R.id.event_category_view);
        mDadeLine = (EditText) findViewById(R.id.dead_event_view);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mButton  = (Button) findViewById(R.id.go_view);
        colorSeekBar = (ColorSeekBar) findViewById(R.id.colorSlider);
        seekBar = (TickSeekBar) findViewById(R.id.listener);
        seekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                Log.e(LOG_TAG,"the position : " + seekParams.tickText);
            }

            @Override
            public void onStartTrackingTouch(TickSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(TickSeekBar seekBar) {

            }
        });
        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int colorBarPosition, int alphaBarPosition, int color) {
                Log.e(LOG_TAG,"The color : " +  colorSeekBar.getColor());
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String event_name = mNameOfEvent.getText().toString();
                String event_place = mNameOfLocation.getText().toString();
                String event_category = mCategoryEvent.getText().toString();
                String event_deadline = mDadeLine.getText().toString();


                if (event_name.isEmpty() && event_place.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill the box",Toast.LENGTH_LONG).show();
                }else{

                    Event event = new Event(event_name,event_place);
                    mDatabase.child(Event.class.getSimpleName()).child(event_name)
                            .setValue(event);
                    Toast.makeText(AddActivity.this,"The data has been saved",Toast.LENGTH_LONG)
                            .show();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(AddActivity.this,ShowEventActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}