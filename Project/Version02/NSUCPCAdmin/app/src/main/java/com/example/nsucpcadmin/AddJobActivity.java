package com.example.nsucpcadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AddJobActivity extends AppCompatActivity {

    private AutoCompleteTextView deptList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);


        deptList = findViewById(R.id.departmentListId);


        //DropDown list Start

        String[] genderListString = getResources().getStringArray(R.array.dept_list);
        ArrayAdapter genderlListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, genderListString);
        deptList.setAdapter(genderlListAdapter);

//DropDown list Ends
    }
}