package com.example.nsucpcstudent.DetailsActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nsucpcstudent.R;
import com.example.nsucpcstudent.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExperiencesDetailsActivity extends AppCompatActivity {

    private EditText companyName, companyDepartment, designation, yearsOfExperience;
    private Button saveButton;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiences_details);

        companyName = findViewById(R.id.experienceCompanyNameId);
        companyDepartment = findViewById(R.id.experienceDepartmentId);
        designation = findViewById(R.id.experienceDesignationId);
        yearsOfExperience = findViewById(R.id.experienceYearsOfExperienceId);
        saveButton = findViewById(R.id.experienceSaveButtonId);


        databaseReference = FirebaseDatabase.getInstance().getReference("Experience Information");




    }

    public void save(View view) {

        saveData();

    }

    private void saveData() {

        Student student;

        try {
            String key = databaseReference.push().getKey();

            student = new Student(companyName.getText().toString(),companyDepartment.getText().toString(),designation.getText().toString(),yearsOfExperience.getText().toString());

            databaseReference.child(key).setValue(student);

            Toast.makeText(getApplicationContext(), "Your Experience Info is added", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){

            Toast.makeText(this, "There Is a Error", Toast.LENGTH_SHORT).show();
        }
    }
}