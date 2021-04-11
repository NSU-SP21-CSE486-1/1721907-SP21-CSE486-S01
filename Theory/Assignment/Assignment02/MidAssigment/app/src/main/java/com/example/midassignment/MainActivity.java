package com.example.midassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.midassignment.Room.MyDatabase;
import com.example.midassignment.Room.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {


    private AutoCompleteTextView schoolList;
    private AutoCompleteTextView deptList;
    private Button nextButton;
    private EditText fullName, studentId, nid, date;


    public static final String first_name = "com.example.midassignment.first_name";
    public static final String first_studentId = "com.example.midassignment.first_studentId";
    public static final String first_schoolList = "com.example.midassignment.first_schoolList";
    public static final String first_deptList = "com.example.midassignment.first_deptList";
    public static final String first_date = "com.example.midassignment.first_date";
    public static final String first_nid = "com.example.midassignment.first_nid";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        nextButton = findViewById(R.id.next_button);
        schoolList = findViewById(R.id.School_list_Id);
        deptList = findViewById(R.id.dept_List_Id);
        fullName = findViewById(R.id.full_name);
        studentId = findViewById(R.id.student_Id);
        nid = findViewById(R.id.nid_Id);
        date = findViewById(R.id.datePickerEditText);



//        DropDown list start

        String[] schoolListString = getResources().getStringArray(R.array.school_list);
        String[] eceListString = getResources().getStringArray(R.array.ece_dept_list);
        String[] sbeListString = getResources().getStringArray(R.array.sbe_dept_list);
        String[] shssListString = getResources().getStringArray(R.array.shss_dept_list);
        String[] shlsListString = getResources().getStringArray(R.array.shls_dept_list);

        ArrayAdapter schoolListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, schoolListString);
        ArrayAdapter ecelListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eceListString);
        ArrayAdapter sbeListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sbeListString);
        ArrayAdapter shssListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shssListString);
        ArrayAdapter shlsListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shlsListString);


        schoolList.setAdapter(schoolListAdapter);
        schoolList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    deptList.setAdapter(ecelListAdapter);
                }

                else if (position == 1) {
                    deptList.setAdapter(sbeListAdapter);
                }

                else if (position == 2) {
                    deptList.setAdapter(shssListAdapter);
                }

                else if (position == 3) {
                    deptList.setAdapter(shlsListAdapter);
                }

            }
        });

//        Drop Down List Ends

    }


    public void next(View view) {

//Pass the Intents Starts

        String pass_name = fullName.getText().toString();
        String pass_studentId = studentId.getText().toString();
        String pass_schoollist = schoolList.getText().toString();
        String pass_deptList = deptList.getText().toString();
        String pass_date = date.getText().toString();
        String pass_nid = nid.getText().toString();


        Intent intent = new Intent(MainActivity.this, ContactActivity.class);

        intent.putExtra(first_name, pass_name);
        intent.putExtra(first_studentId, pass_studentId);
        intent.putExtra(first_schoolList, pass_schoollist);
        intent.putExtra(first_deptList, pass_deptList);
        intent.putExtra(first_date, pass_date);
        intent.putExtra(first_nid, pass_nid);

        startActivity(intent);

//Pass the Intents Ends

    }


}