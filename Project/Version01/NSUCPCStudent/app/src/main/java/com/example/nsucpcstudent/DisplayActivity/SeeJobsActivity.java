package com.example.nsucpcstudent.DisplayActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nsucpcstudent.Adapter.JobAdapter;
import com.example.nsucpcstudent.Authentication.LoginActivity;
import com.example.nsucpcstudent.DetailsActivity.WholeInfoActivity;
import com.example.nsucpcstudent.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SeeJobsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_jobs);

        mAuth = FirebaseAuth.getInstance();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Jobs pdf");


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String fileName = snapshot.getKey();
                String url = snapshot.getValue().toString();

                ((JobAdapter)recyclerView.getAdapter()).update(fileName,url);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(SeeJobsActivity.this));

        JobAdapter jobAdapter = new JobAdapter(recyclerView,SeeJobsActivity.this,new ArrayList<String>(),new ArrayList<String >());
        recyclerView.setAdapter(jobAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.signOutMenuId)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.myProfileMenuId)
        {
            Intent intent = new Intent(getApplicationContext(), WholeInfoActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}