package com.example.nsucpcstudent.DetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nsucpcstudent.Authentication.LoginActivity;
import com.example.nsucpcstudent.DisplayActivity.AcademicDisplayActivity;
import com.example.nsucpcstudent.DisplayActivity.ContactDisplayActivity;
import com.example.nsucpcstudent.DisplayActivity.ExperienceDisplayActivity;
import com.example.nsucpcstudent.DisplayActivity.PersonalDisplayActivity;
import com.example.nsucpcstudent.DisplayActivity.SpecialityDisplayActivity;
import com.example.nsucpcstudent.R;
import com.google.firebase.auth.FirebaseAuth;

public class WholeInfoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_info);

        mAuth = FirebaseAuth.getInstance();
    }

    public void personal(View view) {

        Intent intent = new Intent(getApplicationContext(), PersonalDisplayActivity.class);
        startActivity(intent);
    }

    public void contact(View view) {
        Intent intent = new Intent(getApplicationContext(), ContactDisplayActivity.class);
        startActivity(intent);
    }

    public void academic(View view) {
        Intent intent = new Intent(getApplicationContext(), AcademicDisplayActivity.class);
        startActivity(intent);
    }

    public void specialiazion(View view) {
        Intent intent = new Intent(getApplicationContext(), SpecialityDisplayActivity.class);
        startActivity(intent);
    }

    public void experience(View view) {
        Intent intent = new Intent(getApplicationContext(), ExperienceDisplayActivity.class);
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