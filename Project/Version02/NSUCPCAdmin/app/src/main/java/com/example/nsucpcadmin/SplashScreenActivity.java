package com.example.nsucpcadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nsucpcadmin.Authentication.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private Button signUp, skip;
    private TextView nsuCpc, showYourTalent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        signUp = findViewById(R.id.signUpId);
        nsuCpc = findViewById(R.id.nsuCpcId);

    }

    public void signUp(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

   }