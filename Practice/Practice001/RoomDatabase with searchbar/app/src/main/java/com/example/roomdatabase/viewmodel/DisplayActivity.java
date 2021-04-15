package com.example.roomdatabase.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Adapter;
import android.widget.EditText;

import com.example.roomdatabase.MainActivity;
import com.example.roomdatabase.R;
import com.example.roomdatabase.Room.MyDatabase;
import com.example.roomdatabase.Room.Student;

import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DisplayAdapter dAdapter;
    private RecyclerView.LayoutManager mLayout;
    EditText searchView;
    CharSequence search = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        recyclerView = findViewById(R.id.recyclerviewId);
        recyclerView.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayout);

        searchView = findViewById(R.id.searchId);

        MyDatabase myDatabase = Room.databaseBuilder(DisplayActivity.this, MyDatabase.class, "StudentDB").allowMainThreadQueries().build();


        List<Student> sfirstName = myDatabase.dao().getStudent();
        dAdapter = new DisplayAdapter(sfirstName,this);
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