package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdatabase.Room.MyDatabase;
import com.example.roomdatabase.Room.Student;
import com.example.roomdatabase.viewmodel.DisplayActivity;
import com.example.roomdatabase.viewmodel.DisplayAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText sFirstName, sLastName, sClass;
    private Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sFirstName = findViewById(R.id.firstNameId);
        sLastName = findViewById(R.id.lastNameId);
        sClass = findViewById(R.id.classId);
        addButton = findViewById(R.id.addButtonId);



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student = new Student(sFirstName.getText().toString(), sLastName.getText().toString(), Integer.parseInt(sClass.getText().toString()));
                MyDatabase myDatabase = Room.databaseBuilder(MainActivity.this, MyDatabase.class, "StudentDB").allowMainThreadQueries().build();

                myDatabase.dao().studentInsertion(student);

                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(intent);

            }
        });




        }
}