package com.example.nsucpcadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WholeInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_information);
    }

    public void existingJob(View view) {
    }

    public void deleteJob(View view) {
    }

    public void meetApplicationDeadline(View view) {
    }

    public void add_job(View view) {

        Intent intent = new Intent(getApplicationContext(),AddJobActivity.class);

    }
}