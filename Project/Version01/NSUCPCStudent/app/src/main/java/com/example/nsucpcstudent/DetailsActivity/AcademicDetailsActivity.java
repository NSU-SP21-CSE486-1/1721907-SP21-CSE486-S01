package com.example.nsucpcstudent.DetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nsucpcstudent.R;
import com.example.nsucpcstudent.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcademicDetailsActivity extends AppCompatActivity {

    private EditText institutionName, cgpa, passingYear, duration;
    private Button saveButton;

    private AutoCompleteTextView schoolList;
    private AutoCompleteTextView deptList;
    private AutoCompleteTextView degreeList;


    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_details);

        institutionName = findViewById(R.id.academicInstitutionNameId);
        cgpa = findViewById(R.id.academicCgpaId);
        passingYear = findViewById(R.id.academicPassingYearId);
        duration = findViewById(R.id.academicDurationId);
        saveButton = findViewById(R.id.academicSaveButtonId);

        schoolList = findViewById(R.id.academicSchoolId);
        deptList = findViewById(R.id.academicDepartmentId);
        degreeList = findViewById(R.id.academicDegreeTittleId);

        databaseReference = FirebaseDatabase.getInstance().getReference("Academic Information");




//DropDown list start

        String[] schoolListString = getResources().getStringArray(R.array.school_list);
        String[] eceListString = getResources().getStringArray(R.array.ece_dept_list);
        String[] sbeListString = getResources().getStringArray(R.array.sbe_dept_list);
        String[] shssListString = getResources().getStringArray(R.array.shss_dept_list);
        String[] shlsListString = getResources().getStringArray(R.array.shls_dept_list);
        String[] degreeListString = getResources().getStringArray(R.array.degree_list);


        ArrayAdapter schoolListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, schoolListString);
        ArrayAdapter ecelListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eceListString);
        ArrayAdapter sbeListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sbeListString);
        ArrayAdapter shssListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shssListString);
        ArrayAdapter shlsListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shlsListString);
        ArrayAdapter degreeListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, degreeListString);



        schoolList.setAdapter(schoolListAdapter);
        degreeList.setAdapter(degreeListAdapter);
        schoolList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    deptList.setAdapter(ecelListAdapter);
                }

                else if (position == 1) {
                    deptList.setAdapter(sbeListAdapter);
                }

                else if (position == 2) {
                    deptList.setAdapter(shssListAdapter);
                }

                else if (position == 3) {
                    deptList.setAdapter(shlsListAdapter);
                }

            }
        });

//Drop Down List Ends



    }


    public void save(View view) {
            saveData();
    }

    private void saveData() {

        Student student;

        try {
             String key = databaseReference.push().getKey();

             student = new Student(institutionName.getText().toString(),degreeList.getText().toString(),schoolList.getText().toString(),deptList.getText().toString(),cgpa.getText().toString(),
                     passingYear.getText().toString(),duration.getText().toString());

             databaseReference.child(key).setValue(student);

            Toast.makeText(getApplicationContext(), "Your Personal Info is added", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){

            Toast.makeText(this, "There Is a Error", Toast.LENGTH_SHORT).show();
        }
    }
}