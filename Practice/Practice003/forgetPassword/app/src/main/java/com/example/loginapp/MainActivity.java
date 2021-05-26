package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText loginEmail, loginpassword;
    private TextView dontAccount,forgetPassword;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Log In Activity");

        mAuth = FirebaseAuth.getInstance();

        loginButton = findViewById(R.id.loginButtonId);
        loginEmail = findViewById(R.id.loginEmailId);
        loginpassword = findViewById(R.id.loginPasswordId);
        dontAccount = findViewById(R.id.dontAccountId);
        forgetPassword = findViewById(R.id.recoveryEmailId);
    }

    public void login(View view) {

        switch (view.getId()){

            case R.id.loginButtonId:
                userLogin();
            break;

            case R.id.dontAccountId:
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);

                break;
        }

    }
    private void userLogin() {
    String email = loginEmail.getText().toString().trim();
    String password = loginpassword.getText().toString().trim();

        if(email.isEmpty()){
        loginEmail.setError("Please Enter a Valid Email");
        loginEmail.requestFocus();
        return;
    }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        loginEmail.setError("Please Enter a Valid Email");
        loginEmail.requestFocus();
        return;
    }

        if(password.isEmpty()){
        loginpassword.setError("please Enter a Strong Password");
        loginpassword.requestFocus();
        return;
    }
        if(password.length()<6){
        loginpassword.setError("Password Should Be 6 Characters");
        loginpassword.requestFocus();
        return;
    }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "There Is a Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

}

    public void forgetPassword(View view) {
        Intent intent = new Intent(getApplicationContext(),ForgetPasswordActivity.class);
        startActivity(intent);

    }
}