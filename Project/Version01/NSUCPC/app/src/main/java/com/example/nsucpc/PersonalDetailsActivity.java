package com.example.nsucpc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class PersonalDetailsActivity extends AppCompatActivity {

    private AutoCompleteTextView genderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        genderList = findViewById(R.id.genderListId);

        String[] genderListString = getResources().getStringArray(R.array.gender_list);

        ArrayAdapter genderlListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, genderListString);

        genderList.setAdapter(genderlListAdapter);

    }

    public void save(View view) {
    }
}