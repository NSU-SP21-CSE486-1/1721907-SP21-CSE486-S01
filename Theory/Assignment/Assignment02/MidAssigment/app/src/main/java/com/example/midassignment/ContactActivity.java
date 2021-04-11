package com.example.midassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.midassignment.Room.Contact;
import com.example.midassignment.Room.MyDatabase;
import com.example.midassignment.Room.Student;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private EditText phoneNumber;

    RecyclerView recyclerView;
    List<versions> versionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        phoneNumber = findViewById(R.id.phone_Id);
        recyclerView = findViewById(R.id.presentAddress_recycleview_Id);

        initData();
        setRecyclerView();


    }

    private void setRecyclerView() {
        versionsAdapter versionsAdapter = new versionsAdapter(versionsList);
        recyclerView.setAdapter(versionsAdapter);
        recyclerView.setHasFixedSize(true);

    }

    private void initData() {

        versionsList = new ArrayList<>();

        versionsList.add(new versions("Present Address", "", "", "", "", "", "", ""));
        versionsList.add(new versions("Parmanent Address", "", "", "", "", "", "", ""));
    }



    public void save(View view) {
//Database Starts

//Database Insertion
        Contact contact = new Contact(phoneNumber.getText().toString());
        MyDatabase myDatabase = Room.databaseBuilder(ContactActivity.this, MyDatabase.class, "StudentDB").allowMainThreadQueries().build();

        myDatabase.dao().contactInsertion(contact);

//Database Ends


    }


}