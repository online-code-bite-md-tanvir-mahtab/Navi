package com.tanvircodder.taskclander;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.hardware.camera2.TotalCaptureResult;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {


//    we are going to have the edit text layout..
    EditText f_name ;
    EditText familly_name;
    EditText email_view;
    EditText birth_view;
    EditText country_view;

    EditText gender_picker;


//    creating the instance of the firbase data base and the datarabase reference
    DatabaseReference mDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Generel Info");
        f_name = (EditText) findViewById(R.id.f_name_view);
        familly_name = (EditText) findViewById(R.id.family_view_name);



    }
    public void myClick(View view){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        f_name = (EditText) findViewById(R.id.f_name_view);
        familly_name = (EditText) findViewById(R.id.family_view_name);
        gender_picker = (EditText) findViewById(R.id.gender_picker);
        email_view = (EditText) findViewById(R.id.email_body_view);
        birth_view = (EditText) findViewById(R.id.birth_view_name);
        country_view = (EditText) findViewById(R.id.country_view);
        String first_name = f_name.getText().toString();
        String familly = familly_name.getText().toString();
        String gender = gender_picker.getText().toString();
        String email = email_view.getText().toString();
        String birth = birth_view.getText().toString();
        String country = country_view.getText().toString();

        User user = new User(first_name,familly,email,birth,country,gender);

        mDatabase.child(User.class.getSimpleName()).child(familly)
                .setValue(user);
//        writing the message to the database..//

//        Intent intent = new Intent(MainActivity.this,SleepHabits.class);
//        startActivity(intent);
    }
}