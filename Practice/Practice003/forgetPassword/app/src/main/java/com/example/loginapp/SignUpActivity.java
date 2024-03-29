package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private Button signUpButton;
    private EditText signUpEmail, signUppassword, signupConfirmPassword;
    private TextView alreadyAccount;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        signUpButton = findViewById(R.id.signupButtonid);
        signUpEmail = findViewById(R.id.signupEmailid);
        signUppassword = findViewById(R.id.signupPasswordId);
        alreadyAccount = findViewById(R.id.alreadyAccountId);
        signupConfirmPassword = findViewById(R.id.signupConfirmPasswordId);


    }

    public void signUp(View view) {

        switch (view.getId()){

            case R.id.signupButtonid:
                userRegister();
                break;

            case R.id.alreadyAccountId:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                break;
        }

    }

    private void userRegister() {

        String email = signUpEmail.getText().toString().trim();
        String password = signUppassword.getText().toString().trim();
        String confirmPassword = signupConfirmPassword.getText().toString().trim();

        if(email.isEmpty()){
            signUpEmail.setError("Please Enter a Valid Email");
            signUpEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signUpEmail.setError("Please Enter a Valid Email");
            signUpEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            signUppassword.setError("please Enter a Strong Password");
            signUppassword.requestFocus();
            return;
        }
        if(password.length()<6){
            signUppassword.setError("Password Should Be 6 Characters");
            signUppassword.requestFocus();
            return;
        }

        if(confirmPassword.isEmpty()){
            signupConfirmPassword.setError("please Enter The Same Password");
            signupConfirmPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    {
                        finish();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                } else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(), "User Is Already Registered", Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(getApplicationContext(), "Registration Is Not Successful" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        }

    }

