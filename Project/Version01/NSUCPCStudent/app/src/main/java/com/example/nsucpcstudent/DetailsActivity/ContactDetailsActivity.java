package com.example.nsucpcstudent.DetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nsucpcstudent.Authentication.LoginActivity;
import com.example.nsucpcstudent.R;
import com.example.nsucpcstudent.Model.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactDetailsActivity extends AppCompatActivity {

    private EditText parmanentAddress, presentAddress, phone, alternativePhone, personalEmail;
    private Button saveButton;


    DatabaseReference databaseReference;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);


        parmanentAddress = findViewById(R.id.contactParmanentAddressId);
        presentAddress = findViewById(R.id.contactPresentAddressId);
        phone = findViewById(R.id.contactPhoneNumberId);
        alternativePhone = findViewById(R.id.contactAlternativePhoneNumberId);
        personalEmail = findViewById(R.id.contactPersonalEmailId);
        saveButton = findViewById(R.id.contactSaveButtonId);

        databaseReference = FirebaseDatabase.getInstance().getReference("Contact Information");

        mAuth = FirebaseAuth.getInstance();


    }

    public void save(View view) {
        saveData();

    }

    private void saveData() {
        boolean allow = true;

     String email = personalEmail.getText().toString();

        if(phone.length() != 11){
            phone.setError("You Should Enter a Valid Phone Number");
            allow = false;
        }

        if(alternativePhone.length() != 11){
            alternativePhone.setError("");
            allow = false;
        }

        if(alternativePhone.length() != 11){
            alternativePhone.setError("");
            allow = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            personalEmail.setError("Please Enter a Valid Email");
            allow = false;
        }
        if(allow){

            Student student;

            try {
                String key = databaseReference.push().getKey();

                student = new Student(parmanentAddress.getText().toString(),presentAddress.getText().toString(),phone.getText().toString(),alternativePhone.getText().toString(),
                        personalEmail.getText().toString());

                databaseReference.child(key).setValue(student);

                Toast.makeText(getApplicationContext(), "Your Contact Info is added", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(this, "There Is a Error", Toast.LENGTH_SHORT).show();

            }

        }
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

}