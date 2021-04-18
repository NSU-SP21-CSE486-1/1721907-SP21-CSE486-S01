package com.example.midassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.midassignment.Room.MyDatabase;
import com.example.midassignment.Room.Student;
import com.example.midassignment.viewmodel.DisplayActivity;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {


    private String schoolList;
    private String deptList;
    private String fullName, studentId, date, nid;
    private Button saveButton;
    private EditText phoneNumber;


    RecyclerView recyclerView;
    List<versions> versionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        recyclerView = findViewById(R.id.presentAddress_recycleview_Id);
        saveButton = findViewById(R.id.save_button);
        phoneNumber = findViewById(R.id.phone_Id);


        initData();
        setRecyclerView();


        Intent intent = getIntent();
        fullName = intent.getStringExtra(MainActivity.first_name);
        studentId = intent.getStringExtra(MainActivity.first_studentId);
        schoolList = intent.getStringExtra(MainActivity.first_schoolList);
        deptList = intent.getStringExtra(MainActivity.first_deptList);
        date = intent.getStringExtra(MainActivity.first_date);
        nid = intent.getStringExtra(MainActivity.first_nid);



    }

    private void setRecyclerView() {
        versionsAdapter versionsAdapter = new versionsAdapter(versionsList);
        recyclerView.setAdapter(versionsAdapter);
        recyclerView.setHasFixedSize(true);

    }

    private void initData() {

        versionsList = new ArrayList<>();

        versionsList.add(new versions(getString(R.string.present_address), "", "", "", "", "", "", ""));
        versionsList.add(new versions(getString(R.string.permanent_address), "", "", "", "", "", "", ""));
    }


    public void save(View view) {

        boolean allow = true;
        String pass_phoneNumber = phoneNumber.getText().toString();

        if(phoneNumber.length() != 11){
            phoneNumber.setError(getString(R.string.phone_number_validation));
            allow = false;
        }

        if (allow){
            Student student;

            try {
                student = new Student(fullName, Integer.parseInt(studentId), schoolList, deptList, date, nid, Integer.parseInt(phoneNumber.getText().toString()));
                MyDatabase myDatabase = Room.databaseBuilder(ContactActivity.this, MyDatabase.class, "StudentDB").allowMainThreadQueries().build();

                myDatabase.dao().studentInsertion(student);

                Intent intent = new Intent(ContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
            catch (Exception e){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }



    }
}