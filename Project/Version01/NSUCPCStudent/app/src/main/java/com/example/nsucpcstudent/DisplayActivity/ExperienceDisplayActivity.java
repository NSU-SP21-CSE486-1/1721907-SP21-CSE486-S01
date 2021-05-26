package com.example.nsucpcstudent.DisplayActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsucpcstudent.R;
import com.example.nsucpcstudent.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ExperienceDisplayActivity extends AppCompatActivity {

    private TextView experienceCompanyName,experienceDepartment,experienceDesignation, yearsOfExperience;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_display);


        experienceCompanyName = findViewById(R.id.experienceCompanyNameDisplayId);
        experienceDepartment = findViewById(R.id.experienceDepartmentDisplayId);
        experienceDesignation = findViewById(R.id.experienceDesignationDisplayId);
        yearsOfExperience = findViewById(R.id.experienceYearsOfExperienceDisplayId);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Experience Information");



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Student student = snapshot.getValue(Student.class);
                    experienceCompanyName.setText(dataSnapshot.child("companyName").getValue(String.class));
                    experienceDepartment.setText(dataSnapshot.child("companyDepartment").getValue(String.class));
                    experienceDesignation.setText(dataSnapshot.child("designation").getValue(String.class));
                    yearsOfExperience.setText(dataSnapshot.child("yearsOfExperience").getValue(String.class));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ExperienceDisplayActivity.this, error.getCode(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}