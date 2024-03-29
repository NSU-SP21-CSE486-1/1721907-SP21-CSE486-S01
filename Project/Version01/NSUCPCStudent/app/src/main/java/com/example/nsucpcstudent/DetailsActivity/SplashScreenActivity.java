package com.example.nsucpcstudent.DetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nsucpcstudent.Authentication.LoginActivity;
import com.example.nsucpcstudent.DetailsActivity.AcademicDetailsActivity;
import com.example.nsucpcstudent.DetailsActivity.ContactDetailsActivity;
import com.example.nsucpcstudent.DetailsActivity.ExperiencesDetailsActivity;
import com.example.nsucpcstudent.DetailsActivity.PersonalDetailsActivity;
import com.example.nsucpcstudent.DetailsActivity.SpecialityDetailsActivity;
import com.example.nsucpcstudent.DisplayActivity.AcademicDisplayActivity;
import com.example.nsucpcstudent.DisplayActivity.ContactDisplayActivity;
import com.example.nsucpcstudent.DisplayActivity.ExperienceDisplayActivity;
import com.example.nsucpcstudent.DisplayActivity.PersonalDisplayActivity;
import com.example.nsucpcstudent.DisplayActivity.SeeJobsActivity;
import com.example.nsucpcstudent.DisplayActivity.SpecialityDisplayActivity;
import com.example.nsucpcstudent.R;

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
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void skip(View view) {

        Intent intent = new Intent(getApplicationContext(), SeeJobsActivity.class);
        startActivity(intent);

    }
}