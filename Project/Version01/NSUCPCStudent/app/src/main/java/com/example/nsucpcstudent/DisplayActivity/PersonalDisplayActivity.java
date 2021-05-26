package com.example.nsucpcstudent.DisplayActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class PersonalDisplayActivity extends AppCompatActivity {

    private TextView fullName,fatherName,motherName, dateOfBirth, nid, religion, gender,nationality;
    private String email,password;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_display);

        fullName = findViewById(R.id.personalDisplayNameId);
        fatherName = findViewById(R.id.personalDisplayFatherNameId);
        motherName = findViewById(R.id.personalDisplayMotherNameId);
        dateOfBirth = findViewById(R.id.personalDisplayDateOfBirthId);
        nid = findViewById(R.id.personalDisplayNidId);
        religion = findViewById(R.id.personalDisplayReligionId);
        gender = findViewById(R.id.personalDisplayGenderId);
        nationality = findViewById(R.id.personalDisplayNationalityId);

    firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Personal Information");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Student student = snapshot.getValue(Student.class);
                    fullName.setText(dataSnapshot.child("fullName").getValue(String.class));
                    fatherName.setText(dataSnapshot.child("fatherName").getValue(String.class));
                    motherName.setText(dataSnapshot.child("motherName").getValue(String.class));
                    dateOfBirth.setText(dataSnapshot.child("dateOfBirth").getValue(String.class));
                    nid.setText(dataSnapshot.child("nid").getValue(String.class));
                    religion.setText(dataSnapshot.child("religion").getValue(String.class));
                    gender.setText(dataSnapshot.child("gender").getValue(String.class));
                    nationality.setText(dataSnapshot.child("nationality").getValue(String.class));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(PersonalDisplayActivity.this, error.getCode(), Toast.LENGTH_SHORT).show();
                
            }
        });

    }
}