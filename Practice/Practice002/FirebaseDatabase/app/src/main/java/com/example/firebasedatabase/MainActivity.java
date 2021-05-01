package com.example.firebasedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private TextView info;
    private EditText name, age;
    private Button submit , view;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = findViewById(R.id.infoId);
        name = findViewById(R.id.nameId);
        age = findViewById(R.id.ageId);
        submit = findViewById(R.id.submitButtonId);
        view =findViewById(R.id.viewButtonId);

        databaseReference = FirebaseDatabase.getInstance().getReference("students");
    }

    public void submit(View view) {

        saveData();

    }

    private void saveData() {

    String mName = name.getText().toString().trim();
    String mAge = age.getText().toString().trim();

    String key = databaseReference.push().getKey();

    Student student = new Student(mName, mAge);
    databaseReference.child(key).setValue(student);

        Toast.makeText(getApplicationContext(), "Student Info is added", Toast.LENGTH_SHORT).show();

        name.setText("");
        age.setText("");

    }

    public void view(View view) {

        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
        startActivity(intent);

    }
}