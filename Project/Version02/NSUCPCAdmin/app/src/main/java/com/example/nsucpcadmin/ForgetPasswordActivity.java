package com.example.nsucpcadmin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {


    private Button forgetPassword;
    private TextView backToLogin;
    private EditText recoveryEmail;

    private String email;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        firebaseAuth = FirebaseAuth.getInstance();
        forgetPassword = findViewById(R.id.forgetPasswordButtonId);
        backToLogin = findViewById(R.id.backToLoginId);
        recoveryEmail = findViewById(R.id.recoveryEmailId);

    }


    public void resetPassword(View view) {

        validateEmail();

    }

    private void validateEmail() {

        email = recoveryEmail.getText().toString();

        if(email.isEmpty()){
            recoveryEmail.setError("Please Enter a Valid Email");
            recoveryEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            recoveryEmail.setError("Please Enter a Valid Email");
            recoveryEmail.requestFocus();
            return;
        }

        else{

            forgetPassword();

        }

    }

    private void forgetPassword() {

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isComplete()) {
                            Toast.makeText(ForgetPasswordActivity.this, "Please Check You Email", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(ForgetPasswordActivity.this, "Error! Please Try Again Later" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
    public void back(View view) {

        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }



}