package com.example.midassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView schoolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schoolList = findViewById(R.id.School_list_Id);

        String[] schoolListString = getResources().getStringArray(R.array.school_list);

        ArrayAdapter schoolListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,schoolListString);

        schoolList.setAdapter(schoolListAdapter);

    }
}