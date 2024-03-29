package com.example.midassignment.UIController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.midassignment.R;
import com.example.midassignment.Room.Databases.MyDatabase;
import com.example.midassignment.Room.Models.Student;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DisplayAdapter dAdapter;
    private RecyclerView.LayoutManager mLayout;
    EditText searchView;
    CharSequence search = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recyclerviewId);
        recyclerView.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayout);

        searchView = findViewById(R.id.searchId);


        MyDatabase myDatabase = Room.databaseBuilder(SearchActivity.this, MyDatabase.class, "StudentDB").allowMainThreadQueries().build();


        List<Student> studentId = myDatabase.dao().getStudent();
        dAdapter = new DisplayAdapter(studentId,this);
        recyclerView.setAdapter(dAdapter);

        searchView.addTextChangedListener((new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                dAdapter.getFilter().filter(charSequence);
                search = charSequence;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        }));

    }
}