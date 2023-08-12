package com.example.uas10120200;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class Login extends AppCompatActivity {

    EditText emailEdit,passwordEdit;
    Button loginBtn;
    ProgressBar progressBar;
    TextView signUpTextviewBtn;

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEdit = findViewById(R.id.email_edit);
        passwordEdit = findViewById(R.id.password_edit);
        loginBtn = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progress_bar);
        signUpTextviewBtn = findViewById(R.id.signUp_textview_btn);

        loginBtn.setOnClickListener((v)-> loginUser());
        signUpTextviewBtn.setOnClickListener((v-> startActivity(new Intent(Login.this, SignUp.class))));

    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    void loginUser(){

        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        boolean isValidated = validateData(email,password);
        if (isValidated){
            return;
        }

        loginInFirebase(email,password);
    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    void loginInFirebase(String email, String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        changeInProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeInProgress(false);
                if(task.isSuccessful()){

                   if (firebaseAuth.getCurrentUser().isEmailVerified()){
                       startActivity(new Intent(Login.this, MainActivity.class));
                       finish();
                   }else {
                       Utilities.showToast(Login.this, "Email belum terverifikasi, silahkan verifikasi terlebih dahulu!");
                   }
                }else {
                    Utilities.showToast(Login.this, task.getException().getLocalizedMessage());
                }
            }
        });
    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    void changeInProgress(boolean inProgress){

        if (inProgress){
            progressBar.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
        }else {
            progressBar.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    boolean validateData(String email, String password){

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdit.setError("Email tidak valid!");
            return false;
        }
        if (password.length()<10){
            return false;
        }
        return true;

    }
}