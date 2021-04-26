package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText loginEmail, loginpassword;
    private TextView dontAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Log In Activity");

        loginButton = findViewById(R.id.signupButtonid);
        loginEmail = findViewById(R.id.signupEmailid);
        loginpassword = findViewById(R.id.signupPasswordId);
        dontAccount = findViewById(R.id.dontAccountId);
    }

    public void login(View view) {

        switch (view.getId()){

            case R.id.loginButtonId:
            break;

            case R.id.dontAccountId:
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);

                break;
        }

    }

}