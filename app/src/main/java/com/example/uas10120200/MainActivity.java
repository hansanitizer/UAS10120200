package com.example.uas10120200;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class MainActivity extends AppCompatActivity {

    FloatingActionButton addNoteBtn;
    RecyclerView recyclerView;
    ImageButton menuBtn;
    NoteAdapter noteAdapter;

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_placeholder);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_placeholder:
                    return true;
                case R.id.nav_profile:
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                    return true;
                case R.id.nav_about:
                    startActivity(new Intent(getApplicationContext(), AboutApk.class));
                    return true;
            }
            return false;
        });

        addNoteBtn = findViewById(R.id.add_note_btn);
        recyclerView = findViewById(R.id.recycler_view);
        menuBtn = findViewById(R.id.menu_btn);

        addNoteBtn.setOnClickListener((v)-> startActivity(new Intent(MainActivity.this, NoteDetail.class)));
        menuBtn.setOnClickListener((v)->showMenu());
        setupRecyclerView();
    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    void showMenu(){
        PopupMenu popupMenu  = new PopupMenu(MainActivity.this,menuBtn);
        popupMenu.getMenu().add("Logout");
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getTitle()=="Logout"){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this,Login.class));
                    finish();
                    return true;
                }
                return false;
            }
        });

    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    void setupRecyclerView(){

        Query query = Utilities.getCollectionReferenceForNotes().orderBy("timestamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query,Note.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(options, this);
        recyclerView.setAdapter(noteAdapter);

    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    @Override
    protected void onStart() {
        super.onStart();
        noteAdapter.startListening();
    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    @Override
    protected void onStop() {
        super.onStop();
        noteAdapter.stopListening();
    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    @Override
    protected void onResume() {
        super.onResume();
        noteAdapter.notifyDataSetChanged();
    }
}