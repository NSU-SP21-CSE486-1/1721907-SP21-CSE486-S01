package com.example.midassignment.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.midassignment.R;
import com.example.midassignment.Room.MyDatabase;
import com.example.midassignment.Room.Student;

import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DisplayAdapter dAdapter;
    private RecyclerView.LayoutManager mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        recyclerView = findViewById(R.id.recyclerviewId);
        recyclerView.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayout);


        MyDatabase myDatabase = Room.databaseBuilder(DisplayActivity.this, MyDatabase.class, "StudentDB").allowMainThreadQueries().build();


        List<Student> studentId = myDatabase.dao().getStudent();
        dAdapter = new DisplayAdapter(studentId,this);
        recyclerView.setAdapter(dAdapter);
    }
}