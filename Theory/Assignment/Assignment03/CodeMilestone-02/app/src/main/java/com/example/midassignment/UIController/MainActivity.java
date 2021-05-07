package com.example.midassignment.UIController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.midassignment.LoginActivity;
import com.example.midassignment.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class MainActivity extends AppCompatActivity  {


    private AutoCompleteTextView schoolList;
    private AutoCompleteTextView deptList;
    private Button nextButton;
    private EditText fullName, studentId, nid, date;

    private ImageButton language;

    private FirebaseAuth mAuth;


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
        loadLocale();

        this.setTitle("Registration App");


        nextButton = findViewById(R.id.next_button);
        schoolList = findViewById(R.id.School_list_Id);
        deptList = findViewById(R.id.dept_List_Id);
        fullName = findViewById(R.id.full_name);
        studentId = findViewById(R.id.student_Id);
        nid = findViewById(R.id.nid_Id);
        date = findViewById(R.id.datePickerEditText);

        language = findViewById(R.id.languageId);

        mAuth = FirebaseAuth.getInstance();




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
        boolean allow = true;
        String pass_name = fullName.getText().toString();
        String pass_studentId = studentId.getText().toString();
        String pass_schoollist = schoolList.getText().toString();
        String pass_deptList = deptList.getText().toString();
        String pass_date = date.getText().toString();
        String pass_nid = nid.getText().toString();

        if(studentId.length() !=7){
            studentId.setError(getString(R.string.student_Id_Validation));
            allow = false;
        }
        if(nid.length() != 10){
            nid.setError(getString(R.string.nid_validation));
            allow = false;
        }

        if(allow){
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);

            intent.putExtra(first_name, pass_name);
            intent.putExtra(first_studentId, pass_studentId);
            intent.putExtra(first_schoolList, pass_schoollist);
            intent.putExtra(first_deptList, pass_deptList);
            intent.putExtra(first_date, pass_date);
            intent.putExtra(first_nid, pass_nid);

            startActivity(intent);
        }

//Pass the Intents Ends

    }

    public void view(View view) {

        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);
    }

    public void search(View view) {
        Intent intent= new Intent(this,SearchActivity.class);
        startActivity(intent);
    }


    //Multiple language Support Starts

    public void change(View view) {

        showChangeLanguageDialog();

    }

    private void showChangeLanguageDialog() {

        final String[] listItem = {"English-UK", "English-US", "Bangla-BN"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(i==0){
                    setLocale("EN-UK");
                    recreate();
                }
                else if(i==1){
                    setLocale("EN-US");
                    recreate();
                }
                else if(i==2){
                    setLocale("BN");
                    recreate();
                }

                dialogInterface.dismiss();

            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My Language",language);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String languages = preferences.getString("My Language", "");
        setLocale(languages);
    }
    //Multiple language Support Ends



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
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    //SignOut menu Ends

}