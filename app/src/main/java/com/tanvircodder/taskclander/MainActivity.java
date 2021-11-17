package com.tanvircodder.taskclander;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.hardware.camera2.TotalCaptureResult;
import android.icu.text.UnicodeSetSpanner;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tanvircodder.taskclander.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();


//    we are going to have the edit text layout..
    EditText f_name ;
    EditText familly_name;
    EditText email_view;
    EditText birth_view;
    Spinner country_view_spiner;

    Spinner gender_picker;


//    creating the instance of the firbase data base and the datarabase reference
    DatabaseReference mDatabase ;
//    creating the object of the DatePickerDialog..//
    DatePickerDialog picker;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String namePattern = "[a-zA-Z ]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Generel Info");
        f_name = (EditText) findViewById(R.id.f_name_view);
        familly_name = (EditText) findViewById(R.id.family_view_name);

        birth_view = (EditText) findViewById(R.id.birth_view_name);
        country_view_spiner = (Spinner) findViewById(R.id.spinner);
        gender_picker = (Spinner) findViewById(R.id.spinner2);
        birth_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year  = cldr.get(Calendar.YEAR);

//                creating the date picker dialog..
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    picker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            birth_view.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        }
                    },year,month,day);
                    picker.show();
                }
            }
        });
        country_view_spiner.setOnItemSelectedListener(MainActivity.this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Bangladesh");
        categories.add("India");
        categories.add("Dubai");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        country_view_spiner.setAdapter(dataAdapter);
        Log.e(LOG_TAG,"The selected item is :" + String.valueOf(country_view_spiner.getSelectedItem()));
//        for the gender
        gender_picker.setOnItemSelectedListener(MainActivity.this);
        // Spinner Drop down elements
        List<String> genders = new ArrayList<String>();
        genders.add("Male");
        genders.add("Female");
        genders.add("Other");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, genders);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        gender_picker.setAdapter(dataAdapter2);
        Log.e(LOG_TAG,"The selected item is :" + String.valueOf(gender_picker.getSelectedItem()));




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e(LOG_TAG,"The selected item for country : " + parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO: 11/17/2021 we will do nothing in here
    }
    public void myClick(View view){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        f_name = (EditText) findViewById(R.id.f_name_view);
        familly_name = (EditText) findViewById(R.id.family_view_name);
        gender_picker = (Spinner) findViewById(R.id.spinner2);
        email_view = (EditText) findViewById(R.id.email_body_view);
        birth_view = (EditText) findViewById(R.id.birth_view_name);
        country_view_spiner = (Spinner) findViewById(R.id.spinner);
        String first_name = f_name.getText().toString();
        String familly = familly_name.getText().toString();
        String gender = gender_picker.getSelectedItem().toString();
        String email = email_view.getText().toString();
        String birth = birth_view.getText().toString();
        String country = country_view_spiner.getSelectedItem().toString();

        System.out.println(country);
//        writing the message to the database..//
        if (first_name.isEmpty() && familly.isEmpty() && gender.isEmpty() && email.isEmpty() && birth.isEmpty() && country.isEmpty()){
            Toast.makeText(this,"fille up the box",Toast.LENGTH_LONG).show();
        }else {
            if (email.trim().matches(emailPattern) && first_name.matches(namePattern) && familly.matches(namePattern)){
                User user = new User(first_name,familly,email,birth,country,gender);

                mDatabase.child(User.class.getSimpleName()).child(familly)
                        .setValue(user);
                Intent intent = new Intent(MainActivity.this,SleepHabits.class);
                startActivity(intent);
            }else{
                Log.e(LOG_TAG,"Something went wrong please check it");
            }

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}