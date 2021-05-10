package com.example.midassignment.UIController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.midassignment.Firebase.Models.Student;
import com.example.midassignment.R;
import com.example.midassignment.Session.Session;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    DisplayAdapter dAdapter;
    ArrayList<Student> list;

    EditText searchView;
    CharSequence search = "";

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.setTitle("Registration App");

        recyclerView = findViewById(R.id.recyclerviewId);
        databaseReference = FirebaseDatabase.getInstance().getReference("students");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView = findViewById(R.id.searchId);


        list = new ArrayList<>();
        dAdapter = new DisplayAdapter(this, list);
        recyclerView.setAdapter(dAdapter);

        mAuth = FirebaseAuth.getInstance();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Student student = dataSnapshot.getValue(Student.class);
                    list.add(student);
                }

                dAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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

    //SignOut menu Starts

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.signOutMenuId)
        {
            Session session = new Session(SearchActivity.this);
            session.sessionRemove();
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    //SignOut menu Ends
}