package com.example.midassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<versions> versionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);



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


}