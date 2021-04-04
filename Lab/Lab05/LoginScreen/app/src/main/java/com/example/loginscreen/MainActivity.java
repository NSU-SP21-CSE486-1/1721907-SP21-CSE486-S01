package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email = findViewById(R.id.emailId);
        password = findViewById(R.id.passwordId);
        button = findViewById(R.id.buttonId);

    }

    public void Validation(View view) {

        String memail = email.getText().toString();
        String mpassword = password.getText().toString();

        if (memail.equals("ahnaf@gmail.com") && mpassword.equals("98765")){

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }

        else {
            Toast toast = Toast.makeText(this,R.string.toast,Toast.LENGTH_SHORT);
            toast.show();
        }


    }
}