package com.example.uas10120200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(3000);
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (currentUser==null){
                        startActivity(new Intent(SplashScreen.this, Login.class));
                    }else {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    }
                    finish();

                }catch (Exception e) {

                }
            }
        }; thread.start();
    }
}