package com.example.midassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.midassignment.Room.MyDatabase;
import com.example.midassignment.Room.Student;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {


    private String schoolList;
    private String deptList;
    private String fullName, studentId, date, nid;
    private Button saveButton;
    private EditText phoneNumber;
    private EditText presentCountry,presentDistrict,presentPostOffice,presentPoliceStation,presentPostalCode, presentHouse,presentRoad;
    private EditText permanentCountry,permanentDistrict,permanentPostOffice,permanentPoliceStation,permanentPostalCode, permanentHouse,permanentRoad;


    RecyclerView recyclerView;
    List<versions> versionsList1;

    ConstraintLayout expandableView1;
    CardView cardView1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


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
        versionsAdapter1 versionsAdapter1 = new versionsAdapter1(versionsList1);
        recyclerView.setAdapter(versionsAdapter1);
        recyclerView.setHasFixedSize(true);

    }

    private void initData() {

        versionsList1 = new ArrayList<>();


        versionsList1.add(new versions(getString(R.string.present_address), "", "", "", "", "", "", ""));
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

    public void expand1(View view) {

        if (expandableView1.getVisibility()==View.GONE){
            TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
            expandableView1.setVisibility(View.VISIBLE);
        }
        else {
            TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
            expandableView1.setVisibility(View.GONE);
        }

    }

}