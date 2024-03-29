package com.example.midassignment.UIController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.midassignment.R;
import com.example.midassignment.Firebase.Models.Student;
import com.example.midassignment.Session.Session;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactActivity extends AppCompatActivity {


    private String schoolList;
    private String deptList;
    private String fullName, studentId, date, nid;
    private Button saveButton;
    private EditText phoneNumber;
    private EditText presentCountry,presentDistrict,presentPostOffice,presentPoliceStation,presentPostalCode, presentHouse,presentRoad;
    private EditText permanentCountry,permanentDistrict,permanentPostOffice,permanentPoliceStation,permanentPostalCode, permanentHouse,permanentRoad;

    DatabaseReference databaseReference;


    ConstraintLayout expandableView1;
    ConstraintLayout expandableView2;
    CardView cardView1;
    CardView cardView2;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        this.setTitle("Registration App");


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


        databaseReference = FirebaseDatabase.getInstance().getReference("students");



        Intent intent = getIntent();
        fullName = intent.getStringExtra(MainActivity.first_name);
        studentId = intent.getStringExtra(MainActivity.first_studentId);
        schoolList = intent.getStringExtra(MainActivity.first_schoolList);
        deptList = intent.getStringExtra(MainActivity.first_deptList);
        date = intent.getStringExtra(MainActivity.first_date);
        nid = intent.getStringExtra(MainActivity.first_nid);

        mAuth = FirebaseAuth.getInstance();



    }


    public void save(View view) {

        saveData();
    }

  private void saveData(){


        boolean allow = true;

        if(phoneNumber.length() != 11){
            phoneNumber.setError(getString(R.string.phone_number_validation));
            allow = false;
        }

        if (allow){
            Student student;

            try {
                String key = databaseReference.push().getKey();

                student = new Student(fullName, Integer.parseInt(studentId), schoolList, deptList, date, nid, Integer.parseInt(phoneNumber.getText().toString()),presentCountry.getText().toString(),
                        presentDistrict.getText().toString(),presentPostOffice.getText().toString(),presentPoliceStation.getText().toString(),presentPostalCode.getText().toString(),presentHouse.getText().toString(),presentRoad.getText().toString(),
                        permanentCountry.getText().toString(),permanentDistrict.getText().toString(),permanentPostOffice.getText().toString(),permanentPoliceStation.getText().toString(),permanentPostalCode.getText().toString(),
                        permanentHouse.getText().toString(),permanentRoad.getText().toString());

                databaseReference.child(key).setValue(student);

                Toast.makeText(getApplicationContext(), "Student Info is added", Toast.LENGTH_SHORT).show();


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

    //SignOut menu Starts

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.signOutMenuId)
        {
            Session session = new Session(ContactActivity.this);
            session.sessionRemove();

            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    //SignOut menu Ends

}