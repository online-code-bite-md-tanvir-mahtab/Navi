package com.tanvircodder.taskclander;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class ShowEventActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private TextView mEventName, mPlaceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        mEventName = (TextView) findViewById(R.id.name_event_view);
        mPlaceName = (TextView) findViewById(R.id.place_of_the_event);
    }

}