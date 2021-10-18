package com.tanvircodder.taskclander;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText email_text;
    private EditText password_text;

    private Button continue_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Sign Up");
        firebaseAuth = FirebaseAuth.getInstance();

        email_text = (EditText) findViewById(R.id.email_edit_view);
        password_text = (EditText) findViewById(R.id.password_edit_view);

        continue_button =(Button) findViewById(R.id.button_continue);

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_text.getText().toString();
                String password = password_text.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
//                                    we have to do something..
                                    Toast.makeText(SignupActivity.this,"There is some problem ",Toast.LENGTH_LONG)
                                            .show();
                                }else{
                                    startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}