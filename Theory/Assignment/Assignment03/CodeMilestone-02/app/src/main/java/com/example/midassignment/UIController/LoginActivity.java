package com.example.midassignment.UIController;

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

import com.example.midassignment.R;
import com.example.midassignment.Session.Session;
import com.example.midassignment.Session.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText loginEmail, loginpassword;
    private TextView dontAccount;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setTitle("Registration App");

        mAuth = FirebaseAuth.getInstance();

        loginButton = findViewById(R.id.loginButtonId);
        loginEmail = findViewById(R.id.loginEmailId);
        loginpassword = findViewById(R.id.loginPasswordId);
        dontAccount = findViewById(R.id.dontAccountId);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Session session = new Session(LoginActivity.this);
        String email = session.getSession();

        if (email != ""){

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }
        else {

        }

    }

    public void login(View view) {

            switch (view.getId()) {

                case R.id.loginButtonId:
                    userLogin();
                    break;

                case R.id.dontAccountId:
                    Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                    startActivity(intent);

                    break;
            }
        }

    private void userLogin() {
        String email = loginEmail.getText().toString().trim();
        String password = loginpassword.getText().toString().trim();

        if(email.isEmpty()){
            loginEmail.setError("Please Enter a Valid Email");
            loginEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginEmail.setError("Please Enter a Valid Email");
            loginEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            loginpassword.setError("Your Credential Doesn't Match");
            loginpassword.requestFocus();
            return;
        }
        if(password.length()<8){
            loginpassword.setError("Your Credential Doesn't Match");
            loginpassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    User user = new User(email,password);
                    Session session = new Session(LoginActivity.this);
                    session.sessionSave(user);

                    finish();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You Have No Account. Please SignUp First", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


}