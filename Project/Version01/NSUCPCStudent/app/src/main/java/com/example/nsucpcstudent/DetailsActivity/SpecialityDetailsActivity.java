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

public class SpecialityDetailsActivity extends AppCompatActivity {

    private EditText skillDescription, extraCurrucular, personalAchievment;
    private Button saveButton;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality_details);

        skillDescription = findViewById(R.id.specialityskillDescriptionId);
        extraCurrucular = findViewById(R.id.specialityExtraCurricularActivitiesId);
        personalAchievment = findViewById(R.id.specialityPersonalAchievementId);
        saveButton = findViewById(R.id.specialitySaveButtonId);

        databaseReference = FirebaseDatabase.getInstance().getReference("Specilaity Information");

    }

    public void save(View view) {

        saveData();

    }

    private void saveData() {

        Student student;

        try {

            String key = databaseReference.push().getKey();

            student = new Student(skillDescription.getText().toString(), extraCurrucular.getText().toString(), personalAchievment.getText().toString());

            databaseReference.child(key).setValue(student);

            Toast.makeText(getApplicationContext(), "Your Speciality Info is added", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {

            Toast.makeText(this, "There Is a Error", Toast.LENGTH_SHORT).show();
        }

    }


    public void addSkills(View view) {
    }
}

