package com.example.nsucpcstudent.DisplayActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsucpcstudent.Authentication.LoginActivity;
import com.example.nsucpcstudent.R;
import com.example.nsucpcstudent.Model.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SpecialityDisplayActivity extends AppCompatActivity {

    private TextView skillDescription, extraCurricular, personalAchievements;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality_display);


        skillDescription = findViewById(R.id.specialityskillDescriptionDisplayId);
        extraCurricular = findViewById(R.id.specialityExtraCurricularActivitiesDisplayId);
        personalAchievements = findViewById(R.id.specialityPersonalAchievementDisplayId);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Specilaity Information");
        mAuth = FirebaseAuth.getInstance();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Student student = snapshot.getValue(Student.class);
                    skillDescription.setText(dataSnapshot.child("skillDescription").getValue(String.class));
                    extraCurricular.setText(dataSnapshot.child("extraCurricular").getValue(String.class));
                    personalAchievements.setText(dataSnapshot.child("personalAchievements").getValue(String.class));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(SpecialityDisplayActivity.this, error.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.signOutMenuId)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void edit(View view) {
    }
}
