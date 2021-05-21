package com.example.nsucpc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class AcademicDetailsActivity extends AppCompatActivity {

    private String fullName, fatherName, motherName, dateOfBirth, nid, religion, gender, nationality;
    private EditText institutionName, cgpa, passingYear, duration;
    private Button saveButton;

    private AutoCompleteTextView schoolList;
    private AutoCompleteTextView deptList;
    private AutoCompleteTextView degreeList;


    public static final String personInstitutionName = "com.example.nsucpc.personInstitutionName";
    public static final String personDegreeTittle = "com.example.nsucpc.personDegreeTittle";
    public static final String personSchool = "com.example.nsucpc.personSchool";
    public static final String personDepartment = "com.example.nsucpc.personDepartment";
    public static final String personCgpa= "com.example.nsucpc.personCgpa";
    public static final String personPassingYear = "com.example.nsucpc.personPassingYear";
    public static final String personDuration ="com.example.nsucpc.personDuration";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_details);

        institutionName = findViewById(R.id.academicInstitutionNameId);
        cgpa = findViewById(R.id.academicCgpaId);
        passingYear = findViewById(R.id.academicPassingYearId);
        duration = findViewById(R.id.academicDurationId);

        schoolList = findViewById(R.id.academicSchoolId);
        deptList = findViewById(R.id.academicDepartmentId);
        degreeList = findViewById(R.id.academicDegreeTittleId);



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

        Intent intent = getIntent();
        fullName = intent.getStringExtra(PersonalDetailsActivity.personName);
        fatherName = intent.getStringExtra(PersonalDetailsActivity.personFatherName);
        motherName = intent.getStringExtra(PersonalDetailsActivity.personMotherName);
        dateOfBirth = intent.getStringExtra(PersonalDetailsActivity.personDateOfBirth);
        nid = intent.getStringExtra(PersonalDetailsActivity.personNid);
        religion = intent.getStringExtra(PersonalDetailsActivity.personReligion);
        gender = intent.getStringExtra(PersonalDetailsActivity.personGender);
        nationality = intent.getStringExtra(PersonalDetailsActivity.personNationality);

    }


    public void save(View view) {

//Pass the Intents Starts

        boolean allow = true;
        String passInstitutionName = institutionName.getText().toString();
        String passDegreeTittle = degreeList.getText().toString();
        String passSchool = schoolList.getText().toString();
        String passDepartment = deptList.getText().toString();
        String passCgpa = cgpa.getText().toString();
        String passPassingYear = passingYear.getText().toString();
        String passDuration = duration.getText().toString();

        if (allow){

            Intent intent = new Intent(getApplicationContext(),ContactDetailsActivity.class);

            intent.putExtra(personInstitutionName,passInstitutionName);
            intent.putExtra(personDegreeTittle,passDegreeTittle);
            intent.putExtra(personSchool,passSchool);
            intent.putExtra(personDepartment,passDepartment);
            intent.putExtra(personCgpa,passCgpa);
            intent.putExtra(personPassingYear,passPassingYear);
            intent.putExtra(personDuration,passDuration);

            startActivity(intent);

        }

//Pass the Intents Ends


    }
}