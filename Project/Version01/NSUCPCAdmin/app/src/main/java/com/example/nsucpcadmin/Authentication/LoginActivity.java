package com.example.nsucpcadmin.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsucpcadmin.R;
import com.example.nsucpcadmin.UIController.WholeInformationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private Button loginButton;
    private EditText loginEmail, loginPassword;
    private TextView dontAccount;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();

        loginButton = findViewById(R.id.loginButtonId);
        loginEmail = findViewById(R.id.loginEmailId);
        loginPassword = findViewById(R.id.loginPasswordId);

    }

    public void login(View view) {

        switch (view.getId()) {

            case R.id.loginButtonId:
                userLogin();
                break;

        }

    }

    private void userLogin() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if (email.isEmpty()) {
            loginEmail.setError("Please Enter a Valid Email");
            loginEmail.requestFocus();
            return;
        }
        if (!email.equals("nazmul.hasan@northsouth.edu")) {
            loginEmail.setError("Please Enter a Valid Email");
            loginEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            loginPassword.setError("Your Credential Doesn't Match");
            loginPassword.requestFocus();
            return;
        }
        if (password.length() < 8) {
            loginPassword.setError("Your Credential Doesn't Match");
            loginPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), WholeInformationActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "You Have No Account. Please SignUp First", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    public void forgetPassword(View view) {

        Intent intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
        startActivity(intent);

    }
}