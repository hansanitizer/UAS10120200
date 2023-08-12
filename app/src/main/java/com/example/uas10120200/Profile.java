package com.example.uas10120200;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class Profile extends AppCompatActivity {

    FloatingActionButton backHomeBtn;
    RecyclerView recyclerView;
    ImageButton menuBtn;

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_placeholder);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_profile:
                     return true;
                case R.id.nav_about:
                     startActivity(new Intent(getApplicationContext(), AboutApk.class));
                     return true;
            }
            return false;
        });

        backHomeBtn = findViewById(R.id.add_note_btn);
        recyclerView = findViewById(R.id.recycler_view);
        menuBtn = findViewById(R.id.menu_btn);

        backHomeBtn.setOnClickListener((v)-> startActivity(new Intent(Profile.this, MainActivity.class)));
    }

}