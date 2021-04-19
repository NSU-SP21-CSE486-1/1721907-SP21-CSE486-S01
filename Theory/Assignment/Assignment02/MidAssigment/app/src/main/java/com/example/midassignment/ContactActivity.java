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



    ConstraintLayout expandableView1;
    ConstraintLayout expandableView2;
    CardView cardView1;
    CardView cardView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        phoneNumber = findViewById(R.id.phone_Id);
        saveButton = findViewById(R.id.save_button);

        expandableView1 = findViewById(R.id.expandable1);
        expandableView2 = findViewById(R.id.expandable2);
        cardView1 = findViewById(R.id.Cardview1);
        cardView2 = findViewById(R.id.Cardview2);

        presentCountry = findViewById(R.id.presentCountry_Id);
        presentDistrict = findViewById(R.id.presentDistrict_Id);
        presentPostOffice = findViewById(R.id.presentPostOffice_Id);
        presentPoliceStation = findViewById(R.id.presentPoliceStation_Id);
        presentPostalCode = findViewById(R.id.presentPostalCode_Id);
        presentHouse = findViewById(R.id.presentHouse_Id);
        presentRoad = findViewById(R.id.presentRoad_Id);

        permanentCountry = findViewById(R.id.permanentCountry_Id);
        permanentDistrict = findViewById(R.id.permanentDistrict_Id);
        permanentPostOffice = findViewById(R.id.permanentPostOffice_Id);
        permanentPoliceStation = findViewById(R.id.permanentPoliceStation_Id);
        permanentPostalCode = findViewById(R.id.permanentPostalCode_Id);
        permanentHouse = findViewById(R.id.permanentHouse_Id);
        permanentRoad = findViewById(R.id.permanentRoad_Id);



        Intent intent = getIntent();
        fullName = intent.getStringExtra(MainActivity.first_name);
        studentId = intent.getStringExtra(MainActivity.first_studentId);
        schoolList = intent.getStringExtra(MainActivity.first_schoolList);
        deptList = intent.getStringExtra(MainActivity.first_deptList);
        date = intent.getStringExtra(MainActivity.first_date);
        nid = intent.getStringExtra(MainActivity.first_nid);



    }


    public void save(View view) {

        boolean allow = true;

        if(phoneNumber.length() != 11){
            phoneNumber.setError(getString(R.string.phone_number_validation));
            allow = false;
        }

        if (allow){
            Student student;

            try {
                student = new Student(fullName, Integer.parseInt(studentId), schoolList, deptList, date, nid, Integer.parseInt(phoneNumber.getText().toString()),presentCountry.getText().toString(),
                        presentDistrict.getText().toString(),presentPostOffice.getText().toString(),presentPoliceStation.getText().toString(),presentPostalCode.getText().toString(),presentHouse.getText().toString(),presentRoad.getText().toString(),
                        permanentCountry.getText().toString(),permanentDistrict.getText().toString(),permanentPostOffice.getText().toString(),permanentPoliceStation.getText().toString(),permanentPostalCode.getText().toString(),
                        permanentHouse.getText().toString(),permanentRoad.getText().toString());
                MyDatabase myDatabase = Room.databaseBuilder(ContactActivity.this, MyDatabase.class, "StudentDB").allowMainThreadQueries().build();

                myDatabase.dao().studentInsertion(student);

                Intent intent = new Intent(ContactActivity.this, MainActivity.class);
                startActivity(intent);

           }
           catch (Exception e){
                Toast.makeText(this, "There Is a Error", Toast.LENGTH_SHORT).show();
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

    public void expand2(View view) {

        if (expandableView2.getVisibility()==View.GONE){
            TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
            expandableView2.setVisibility(View.VISIBLE);
        }
        else {
            TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
            expandableView2.setVisibility(View.GONE);
        }

    }

}