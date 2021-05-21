package com.example.nsucpc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class PersonalDetailsActivity extends AppCompatActivity {

    private AutoCompleteTextView genderList;
    private Button SaveButton;
    private EditText fullName, fatherName, motherName, dateOfBirth, nid, religion, nationality;



    public static final String personName = "com.example.nsucpc.personName";
    public static final String personFatherName = "com.example.nsucpc.personFatherName";
    public static final String personMotherName = "com.example.nsucpc.personMotherName";
    public static final String personDateOfBirth ="com.example.nsucpc.personDateOfBirth";
    public static final String personNid = "com.example.nsucpc.personNid";
    public static final String personReligion = "com.example.nsucpc.personReligion";
    public static final String personGender = "com.example.nsucpc.personGender";
    public static final String personNationality = "com.example.nsucpc.personNationality";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        fullName = findViewById(R.id.personalNameId);
        fatherName = findViewById(R.id.personalFatherNameId);
        motherName = findViewById(R.id.personalMotherNameId);
        dateOfBirth= findViewById(R.id.personalDatePickerEditText);
        nid = findViewById(R.id.personalNidId);
        religion = findViewById(R.id.personalReligionId);
        genderList = findViewById(R.id.genderListId);
        nationality = findViewById(R.id.personalNationalityId);


//DropDown list Start

        String[] genderListString = getResources().getStringArray(R.array.gender_list);
        ArrayAdapter genderlListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, genderListString);
        genderList.setAdapter(genderlListAdapter);

//DropDown list Ends

    }

    public void save(View view) {

//Pass the Intents Starts

        boolean allow = true;
        String passName = fullName.getText().toString();
        String passFatherName = fatherName.getText().toString();
        String passMotherName = motherName.getText().toString();
        String passDateOfBirth = dateOfBirth.getText().toString();
        String passNid = nid.getText().toString();
        String passReligion = religion.getText().toString();
        String passGender = genderList.getText().toString();
        String passNationality = nationality.getText().toString();

        if (nid.length() != 10) {
            nid.setError("Please Enter Your New Format (10 Digit) Nid Number");
            nid.requestFocus();
            allow = false;
        }

        if(allow){
            Intent intent = new Intent(getApplicationContext(),AcademicDetailsActivity.class);

            intent.putExtra(personName,passName);
            intent.putExtra(personFatherName,passFatherName);
            intent.putExtra(personMotherName,passMotherName);
            intent.putExtra(personDateOfBirth,passDateOfBirth);
            intent.putExtra(personNid,passNid);
            intent.putExtra(personReligion,passReligion);
            intent.putExtra(personGender,passGender);
            intent.putExtra(personNationality,passNationality);

            startActivity(intent);

        }
//Pass the Intents Ends


    }
}