package com.tanvircodder.taskclander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tanvircodder.taskclander.model.UserLife;

public class LifeArea extends AppCompatActivity {

//    creating the instance of the layout..
    EditText career,family,fun,fitness,add;
    EditText career_hour,family_hour,fun_hour,fitness_hour,add_hour;

//    creating the instance of the firebaseDatabase and the reference of the database..
    private FirebaseDatabase database;
    private DatabaseReference myRef;

//    creating the instance of the model class..
    private UserLife userLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_area);

//        now i am going to assign the value

    }
    public void lifeClick(View view){
        career = (EditText) findViewById(R.id.career_business);
        family = (EditText) findViewById(R.id.family);
        fun = (EditText) findViewById(R.id.fun);
        fitness = (EditText) findViewById(R.id.fitness);
        add = (EditText) findViewById(R.id.add);

        career_hour = (EditText) findViewById(R.id.c_b_hour);
        family_hour = (EditText) findViewById(R.id.family_hour);
        fun_hour = (EditText) findViewById(R.id.fun_hour);
        fitness_hour = (EditText) findViewById(R.id.fitness_hour);
        add_hour =(EditText) findViewById(R.id.add_hour);

        String mFamily = family.getText().toString();
        String mCareer = career.getText().toString();
        String mFun = fun.getText().toString();
        String mFitness = fitness.getText().toString();
        String mAdd = add.getText().toString();

        String family_h = family_hour.getText().toString();
        String career_h = career_hour.getText().toString();
        String fun_h = fun_hour.getText().toString();
        String fitness_h = fitness_hour.getText().toString();
        String add_h = add_hour.getText().toString();
        if (mCareer.isEmpty() && mFamily.isEmpty() && mFun.isEmpty() &&mFitness.isEmpty() &&mAdd.isEmpty() &&career_h.isEmpty()&&family_h.isEmpty()&&fun_h.isEmpty()&&fitness_h.isEmpty()&&add_h.isEmpty()){
            Toast.makeText(this,"Please fill up the box",Toast.LENGTH_LONG)
                    .show();
        }else{

            //        creating the object of the model class
            userLife = new UserLife(mCareer,mFamily,mFun,mFitness,mAdd,career_h,family_h,fun_h,fitness_h,add_h);
//        geting the instance of the database class
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference(UserLife.class.getSimpleName());
            myRef.setValue(userLife);

            Toast.makeText(this,"The data is been stored",Toast.LENGTH_LONG)
                    .show();
            Intent intent = new Intent(LifeArea.this,AddActivity.class);
            startActivity(intent);
        }


    }
}