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
import com.example.nsucpcstudent.DetailsActivity.ContactDetailsActivity;
import com.example.nsucpcstudent.DetailsActivity.SpecialityDetailsActivity;
import com.example.nsucpcstudent.R;
import com.example.nsucpcstudent.Model.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactDisplayActivity extends AppCompatActivity {

    private TextView parmanentAddress,presentAddress,phone, alterphone, personalEmail;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_display);


        parmanentAddress = findViewById(R.id.contactParmanentAddressDisplayId);
        presentAddress = findViewById(R.id.contactPresentAddressDisplayId);
        phone = findViewById(R.id.contactPhoneNumberDisplayId);
        alterphone = findViewById(R.id.contactAlternativePhoneNumberDisplayId);
        personalEmail = findViewById(R.id.contactPersonalEmailDisplayId);



        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Contact Information");
        mAuth = FirebaseAuth.getInstance();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Student student = snapshot.getValue(Student.class);
                    parmanentAddress.setText(dataSnapshot.child("parmanentAddress").getValue(String.class));
                    presentAddress.setText(dataSnapshot.child("presentAddress").getValue(String.class));
                    phone.setText(dataSnapshot.child("phone").getValue(String.class));
                    alterphone.setText(dataSnapshot.child("alternativePhone").getValue(String.class));
                    personalEmail.setText(dataSnapshot.child("personalEmail").getValue(String.class));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ContactDisplayActivity.this, error.getCode(), Toast.LENGTH_SHORT).show();

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

        Intent intent = new Intent(getApplicationContext(), ContactDetailsActivity.class);
        startActivity(intent);

    }
}