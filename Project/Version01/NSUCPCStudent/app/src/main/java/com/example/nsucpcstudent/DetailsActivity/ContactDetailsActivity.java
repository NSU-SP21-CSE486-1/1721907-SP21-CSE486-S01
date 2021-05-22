package com.example.nsucpcstudent.DetailsActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nsucpcstudent.R;
import com.example.nsucpcstudent.R;
import com.example.nsucpcstudent.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactDetailsActivity extends AppCompatActivity {

    private EditText parmanentAddress, presentAddress, phone, alternativePhone, personalEmail;
    private Button saveButton;


    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);


        parmanentAddress = findViewById(R.id.contactParmanentAddressId);
        presentAddress = findViewById(R.id.contactPresentAddressId);
        phone = findViewById(R.id.contactPhoneNumberId);
        alternativePhone = findViewById(R.id.contactAlternativePhoneNumberId);
        personalEmail = findViewById(R.id.contactPersonalEmailId);
        saveButton = findViewById(R.id.academicSaveButtonId);

        databaseReference = FirebaseDatabase.getInstance().getReference("Contact Information");


    }

    public void save(View view) {
        saveData();

    }

    private void saveData() {
        boolean allow = true;

     String email = personalEmail.getText().toString();

        if(phone.length() != 11){
            phone.setError("");
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
}