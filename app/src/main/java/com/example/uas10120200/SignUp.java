package com.example.uas10120200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class SignUp extends AppCompatActivity {

    EditText emailEdit,passwordEdit,confirmPasswordEdit;
    Button signUpBtn;
    ProgressBar progressBar;
    TextView loginTextviewBtn;

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailEdit = findViewById(R.id.email_edit);
        passwordEdit = findViewById(R.id.password_edit);
        confirmPasswordEdit = findViewById(R.id.confirm_password_edit);
        signUpBtn = findViewById(R.id.sign_up_btn);
        progressBar = findViewById(R.id.progress_bar);
        loginTextviewBtn = findViewById(R.id.login_textview_btn);

        signUpBtn.setOnClickListener(v-> signUp());
        loginTextviewBtn.setOnClickListener(v-> finish());

    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    void signUp(){

        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String confirmPassword = confirmPasswordEdit.getText().toString();

        boolean isValidated = validateData(email,password,confirmPassword);
        if (isValidated){
            return;
        }

        signUpInFirebase(email,password);

    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    void signUpInFirebase(String email, String password){
        changeInProgress(true);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUp.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        changeInProgress(false);
                        if (task.isSuccessful()){
                            //akun berhasil dibuat
                            Utilities.showToast(SignUp.this, "Yay! Akunmu sudah siap, lakukan verifikasi melalui Email!");
                            firebaseAuth.getCurrentUser().sendEmailVerification();
                            firebaseAuth.signOut();
                            finish();
                        }else {
                            //akun gagal dibuat
                            Utilities.showToast(SignUp.this,task.getException().getLocalizedMessage());
                        }
                    }
                });

    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    void changeInProgress(boolean inProgress){

        if (inProgress){
            progressBar.setVisibility(View.VISIBLE);
            signUpBtn.setVisibility(View.GONE);
        }else {
            progressBar.setVisibility(View.GONE);
            signUpBtn.setVisibility(View.VISIBLE);
        }
    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    boolean validateData(String email, String password, String confirmPassword){

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdit.setError("Email tidak valid!");
            return false;
        }
        if (password.length()<10){
            passwordEdit.setError("Password terlalu panjang!");
            return false;
        }
        if (password.equals(confirmPassword)){
            confirmPasswordEdit.setError("Password tidak sesuai!");
            return false;
        }
        return true;

    }
}