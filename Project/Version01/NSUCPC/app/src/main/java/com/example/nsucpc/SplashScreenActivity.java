package com.example.nsucpc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    private Button signUp, skip;
    private TextView nsuCpc, showYourTalent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        signUp = findViewById(R.id.signUpId);
        skip = findViewById(R.id.skipId);
        nsuCpc = findViewById(R.id.nsuCpcId);
        showYourTalent = findViewById(R.id.showYourTalentId);


    }

    public void signUp(View view) {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }

    public void skip(View view) {
    }
}