package com.example.nsucpc;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity {


    private Button signUpButton;
    private EditText signUpName, signUpNsuId, signUpNsuEmail,signUpPassword, signUpConfirmPassword, signUpDateOfBirth, signUpNid;
    private TextView alreadyAccount;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mAuth = FirebaseAuth.getInstance();

        signUpButton = findViewById(R.id.signUpButtonId);
        signUpName = findViewById(R.id.signUpNameId);
        signUpNsuId = findViewById(R.id.signUpNsuIdId);
        signUpNsuEmail = findViewById(R.id.signUpNsuEmailId);
        signUpPassword = findViewById(R.id.signUpPasswordId);
        signUpConfirmPassword = findViewById(R.id.signUpConfirmPasswordId);
        signUpDateOfBirth = findViewById(R.id.signUpDatePickerEditText);
        signUpNid = findViewById(R.id.signUpNidId);
        alreadyAccount = findViewById(R.id.alreadyHaveAccountId);



    }

    public void signUp(View view) {

        switch (view.getId()) {

            case R.id.signUpButtonId:
                userRegister();
                break;

            case R.id.alreadyHaveAccountId:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

                break;

        }
    }

    private void userRegister() {

        String name = signUpName.getText().toString().trim();
        String nsuId = signUpNsuId.getText().toString().trim();
        String nsuEmail = signUpNsuEmail.getText().toString().trim();
        String password = signUpPassword.getText().toString().trim();
        String confirmPassword = signUpConfirmPassword.getText().toString().trim();
        String dateOfBirth = signUpDateOfBirth.getText().toString().trim();
        String nid = signUpNid.getText().toString().trim();

        if (name.isEmpty()) {
            signUpName.setError("Please Enter a Valid Name According to NSU");
            signUpName.requestFocus();
            return;
        }

        if (nsuId.isEmpty()) {
            signUpNsuId.setError("Please Enter a Valid NSU Id");
            signUpNsuId.requestFocus();
            return;
        }

        if (nsuId.length() != 7) {
            signUpNsuId.setError("Please Enter Your 7 Digit NSU Id");
            signUpNsuId.requestFocus();
            return;
        }


        if (nsuEmail.isEmpty()) {
            signUpNsuEmail.setError("Please Enter a Valid Email");
            signUpNsuEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(nsuEmail).matches()) {
            signUpNsuEmail.setError("Please Enter a Valid Email");
            signUpNsuEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            signUpPassword.setError("Please Enter a Strong Password");
            signUpPassword.requestFocus();
            return;
        }

        if (password.length() < 8) {
            signUpPassword.setError("Password Should Be 8 Characters");
            signUpPassword.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            signUpConfirmPassword.setError("Please Enter The Same Password");
            signUpConfirmPassword.requestFocus();
            return;
        }

        if (confirmPassword.equals(password)) {

        } else {
            signUpConfirmPassword.setError("Password Doesn't Match");
            signUpConfirmPassword.requestFocus();
            return;
        }

        if (dateOfBirth.isEmpty()) {
            signUpDateOfBirth.setError("Please Enter Your Date Of Birth");
            signUpDateOfBirth.requestFocus();
            return;
        }

        if (nid.isEmpty()) {
            signUpNid.setError("Please Enter a Valid Nid Number");
            signUpNid.requestFocus();
            return;
        }

        if (nid.length() != 10) {
            signUpNid.setError("Please Enter Your New Format (10 Digit) Nid Number");
            signUpNid.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(nsuEmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete (@NonNull Task< AuthResult > task) {
                if (task.isSuccessful()) {

                    {
                        finish();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
                else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You Already Have An Account", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        Toast.makeText(getApplicationContext(), "Registration Is Not Successful" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
