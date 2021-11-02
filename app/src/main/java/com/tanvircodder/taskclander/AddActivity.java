package com.tanvircodder.taskclander;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.tanvircodder.taskclander.model.Event;

public class AddActivity extends AppCompatActivity {

    private EditText mNameOfEvent;
    private EditText mNameOfLocation;
    private EditText mCategoryEvent;
    private EditText mDadeLine;
    private Button mButton;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mNameOfEvent =(EditText) findViewById(R.id.event_name);
        mNameOfLocation = (EditText) findViewById(R.id.where);
        mCategoryEvent = (EditText) findViewById(R.id.event_category_view);
        mDadeLine = (EditText) findViewById(R.id.dead_event_view);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mButton  = (Button) findViewById(R.id.go_view);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String event_name = mNameOfEvent.getText().toString();
                String event_place = mNameOfLocation.getText().toString();
                String event_category = mCategoryEvent.getText().toString();
                String event_deadline = mDadeLine.getText().toString();
                Event event = new Event(event_name,event_place,event_category,event_deadline);
                mDatabase.child(Event.class.getSimpleName()).child(event_name)
                        .setValue(event);
                Toast.makeText(AddActivity.this,"The data has been saved",Toast.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(AddActivity.this,ShowEventActivity.class);
                startActivity(intent);
            }
        });

    }
}