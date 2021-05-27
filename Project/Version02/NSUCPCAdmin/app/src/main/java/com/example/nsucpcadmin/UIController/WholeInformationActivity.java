package com.example.nsucpcadmin.UIController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.nsucpcadmin.Authentication.LoginActivity;
import com.example.nsucpcadmin.R;
import com.example.nsucpcadmin.UIController.AddJobActivity;
import com.google.firebase.auth.FirebaseAuth;

public class WholeInformationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_information);

        mAuth = FirebaseAuth.getInstance();

    }

    public void existingJob(View view) {
    }

    public void deleteJob(View view) {
    }

    public void meetApplicationDeadline(View view) {
    }

    public void add_job(View view) {

        Intent intent = new Intent(getApplicationContext(), AddJobActivity.class);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.signOutMenuId)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}