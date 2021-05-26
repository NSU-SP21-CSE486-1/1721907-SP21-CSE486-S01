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

public class AcademicDisplayActivity extends AppCompatActivity {


    private TextView institutionName,degreeList,schoolList, deptList, cgpa, passingYear, duraion;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_display);


        institutionName = findViewById(R.id.academicInstitutionNameDisplayId);
        degreeList = findViewById(R.id.academicDegreeTittleDisplayId);
        schoolList = findViewById(R.id.academicSchoolDisplayId);
        deptList = findViewById(R.id.academicDepartmentDisplayId);
        cgpa = findViewById(R.id.academicCgpaDisplayId);
        passingYear = findViewById(R.id.academicPassingYearDisplayId);
        duraion = findViewById(R.id.academicDurationDisplayId);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Academic Information");


          databaseReference.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {

                  for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                      Student student = snapshot.getValue(Student.class);
                      institutionName.setText(dataSnapshot.child("institutionName").getValue(String.class));
                      degreeList.setText(dataSnapshot.child("degree").getValue(String.class));
                      schoolList.setText(dataSnapshot.child("school").getValue(String.class));
                      deptList.setText(dataSnapshot.child("department").getValue(String.class));
                      cgpa.setText(dataSnapshot.child("cgpa").getValue(String.class));
                      passingYear.setText(dataSnapshot.child("passingYear").getValue(String.class));
                      duraion.setText(dataSnapshot.child("duration").getValue(String.class));

                  }
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

                  Toast.makeText(AcademicDisplayActivity.this, error.getCode(), Toast.LENGTH_SHORT).show();

              }
          });

    }
}

