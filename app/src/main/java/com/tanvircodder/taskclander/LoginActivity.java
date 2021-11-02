package com.tanvircodder.taskclander;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth ;
    //    we also need to inisilize the auth statelistner.
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    public static final int RC_SIGN_IN = 1;

    //    we are going to have the edit text layout..
    EditText email_text ;
    EditText password_text;

    //    for the button
    Button login_button;
    Button signup_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        geting the firebase auth instance
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
        setContentView(R.layout.activity_login);
        setTitle("Log In");
        email_text = (EditText) findViewById(R.id.email_edit_view);
        password_text = (EditText) findViewById(R.id.password_edit_view);

        login_button = (Button) findViewById(R.id.button_login);
        signup_button = (Button) findViewById(R.id.button_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                we need to do something in here..
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_text.getText().toString();
                final String password = password_text.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
                                    if (password.length()<6){
                                        password_text.setError(getString(R.string.minimum_password));
                                    }else{
                                        Toast.makeText(LoginActivity.this,getString(R.string.auth_failed),Toast.LENGTH_LONG)
                                                .show();
                                    }
                                }else {
                                    Intent intent = new Intent(LoginActivity.this,ShowEventActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}