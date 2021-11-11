package com.tanvircodder.taskclander;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tanvircodder.taskclander.adapter.EventAdapter;
import com.tanvircodder.taskclander.model.Event;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ShowEventActivity extends AppCompatActivity {
    private static final String LOG_TAG = ShowEventActivity.class.getSimpleName();
    private DatabaseReference mDatabase;
    private RecyclerView mRecyclerView;
    private EventAdapter mAdapter;
    private List<Event> mData;
    private TextView mDateTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);
        setTitle("Day View");
        mDateTime = (TextView) findViewById(R.id.day_viewa_current_time);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mAdapter = new EventAdapter(this);
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
            String currentDateandTime = sdf.format(new Date());
            mDateTime.setText(currentDateandTime);
        }





//        now i am going to access the data base with the helpof the reference
//        mDatabase = FirebaseDatabase.getInstance().getReference()
//                .child(Event.class.getSimpleName());
        mDatabase = FirebaseDatabase.getInstance().getReference(Event.class.getSimpleName());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                now i am goig to iterate with foreach
                System.out.println(snapshot.getKey());
                Log.e(LOG_TAG,"The message"+snapshot.getChildrenCount());
                for (DataSnapshot data :
                        snapshot.getChildren()) {
                    Event event = data.getValue(Event.class);
                    mData.add(event);
                    System.out.println(event.getmName());
                    System.out.println(data.getValue());
                }
                System.out.println(mData.size());
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.swapData(mData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}