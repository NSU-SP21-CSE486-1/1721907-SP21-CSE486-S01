package com.example.midassignment;

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

import com.example.midassignment.UIController.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignupActivity extends AppCompatActivity {

    private Button signUpButton;
    private EditText signUpEmail, signUppassword, signupConfirmPassword;
    private TextView alreadyAccount;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.setTitle("Registration App");

        mAuth = FirebaseAuth.getInstance();

        signUpButton = findViewById(R.id.signupButtonid);
        signUpEmail = findViewById(R.id.signupEmailid);
        signUppassword = findViewById(R.id.signupPasswordId);
        alreadyAccount = findViewById(R.id.alreadyAccountId);
        signupConfirmPassword = findViewById(R.id.signupConfirmPasswordId);

    }

    public void signUp(View view) {

        switch (view.getId()) {

            case R.id.signupButtonid:
                userRegister();
                break;

            case R.id.alreadyAccountId:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

                break;
        }

    }

    private void userRegister() {

        String email = signUpEmail.getText().toString().trim();
        String password = signUppassword.getText().toString().trim();
        String confirmPassword = signupConfirmPassword.getText().toString().trim();

        if (email.isEmpty()) {
            signUpEmail.setError("Please Enter a Valid Email");
            signUpEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signUpEmail.setError("Please Enter a Valid Email");
            signUpEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            signUppassword.setError("please Enter a Strong Password");
            signUppassword.requestFocus();
            return;
        }
        if (password.length() > 8) {
            signUppassword.setError("Password Should Be 8 Characters");
            signUppassword.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            signupConfirmPassword.setError("please Enter The Same Password");
            signupConfirmPassword.requestFocus();
            return;
        }

        if (confirmPassword.equals(password)) {

        }
        else{
            signupConfirmPassword.setError("Password Doesn't Match");
            signupConfirmPassword.requestFocus();
            return;
        }



        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete (@NonNull Task< AuthResult > task) {
            if (task.isSuccessful()) {

                {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            } else {
                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                    Toast.makeText(getApplicationContext(), "You Already Have An Account", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getApplicationContext(), "Registration Is Not Successful" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    });
}

}
