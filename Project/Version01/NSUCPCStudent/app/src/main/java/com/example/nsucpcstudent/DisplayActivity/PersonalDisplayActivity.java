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
        databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Student student = snapshot.getValue(Student.class);
                fullName.setText(student.getFullName());
                fatherName.setText(student.getFatherName());
                motherName.setText(student.getMotherName());
                dateOfBirth.setText(student.getDateOfBirth());
                nid.setText(student.getNid());
                religion.setText(student.getReligion());
                gender.setText(student.getGender());
                nationality.setText(student.getNationality());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(PersonalDisplayActivity.this, error.getCode(), Toast.LENGTH_SHORT).show();
                
            }
        });

    }
}