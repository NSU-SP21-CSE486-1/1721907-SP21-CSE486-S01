package com.example.nsucpcstudent.DetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nsucpcstudent.DisplayActivity.PersonalDisplayActivity;
import com.example.nsucpcstudent.R;
import com.example.nsucpcstudent.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PersonalDetailsActivity extends AppCompatActivity {

    private AutoCompleteTextView genderList;
    private Button saveButton;
    private EditText fullName, fatherName, motherName, dateOfBirth, nid, religion, nationality;

     DatabaseReference databaseReference;

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
        saveButton = findViewById(R.id.personalSaveButtonId);


        databaseReference = FirebaseDatabase.getInstance().getReference("Personal Information");


//DropDown list Start

        String[] genderListString = getResources().getStringArray(R.array.gender_list);
        ArrayAdapter genderlListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, genderListString);
        genderList.setAdapter(genderlListAdapter);

//DropDown list Ends

    }

    public void save(View view) {
        saveData();


    }

    private void saveData() {

    boolean allow = true;

    if(nid.length() != 10){
        nid.setError("");
        allow = false;

    }

    if(allow){
        Student student;

        try {
            String key = databaseReference.push().getKey();

            student = new Student(fullName.getText().toString(),fatherName.getText().toString(),motherName.getText().toString(),dateOfBirth.getText().toString(),nid.getText().toString(),
                    religion.getText().toString(),genderList.getText().toString(),nationality.getText().toString());

            databaseReference.child(key).setValue(student);

            Toast.makeText(getApplicationContext(), "Your Personal Info is added", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), PersonalDisplayActivity.class);
            startActivity(intent);
        }
        catch (Exception e){
            Toast.makeText(this, "There Is a Error", Toast.LENGTH_SHORT).show();

        }
    }

    }
}