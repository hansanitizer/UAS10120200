package com.example.uas10120200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class NoteDetail extends AppCompatActivity {

    EditText titleEditText, noteDetailText;
    ImageButton saveNoteBtn;
    TextView pageTitleTextView;
    String title,detail,docId;
    boolean isEditMode = false;
    TextView deleteNoteTextViewBtn;

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        titleEditText = findViewById(R.id.note_title_text);
        noteDetailText = findViewById(R.id.note_detail_text);
        saveNoteBtn = findViewById(R.id.save_note_btn);
        pageTitleTextView = findViewById(R.id.page_title);
        deleteNoteTextViewBtn = findViewById(R.id.delete_note_text_view_btn);

        title = getIntent().getStringExtra("title");
        detail = getIntent().getStringExtra("detail");
        docId = getIntent().getStringExtra("docId");

        if (docId!=null && !docId.isEmpty()){
            isEditMode = true;
        }

        titleEditText.setText(title);
        noteDetailText.setText(detail);
        if (isEditMode){
            pageTitleTextView.setText("Edit Note");
            deleteNoteTextViewBtn.setVisibility(View.VISIBLE);
        }

        saveNoteBtn.setOnClickListener((v)-> saveNote());

        deleteNoteTextViewBtn.setOnClickListener((v)-> deleteNoteFromFirebase());

    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    void saveNote(){
        String noteTitle = titleEditText.getText().toString();
        String noteDetail = noteDetailText.getText().toString();
        if (noteTitle==null || noteTitle.isEmpty()){
            titleEditText.setError("Judul Harus diisi!");
            return;
        }
        Note note = new Note();
        note.setTitle(noteTitle);
        note.setDetail(noteDetail);
        note.setTimestamp(Timestamp.now());

        saveNoteToFirebase(note);

    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    void saveNoteToFirebase(Note note){
        DocumentReference documentReference;
        if (isEditMode){
            documentReference = Utilities.getCollectionReferenceForNotes().document(docId);
        }else
            documentReference = Utilities.getCollectionReferenceForNotes().document();

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Utilities.showToast(NoteDetail.this, "Note baru telah ditambahkan");
                    finish();
                }else {
                    Utilities.showToast(NoteDetail.this, "Note gagal ditambahkan");
                }
            }
        });

    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    void deleteNoteFromFirebase(){
        DocumentReference documentReference;
            documentReference = Utilities.getCollectionReferenceForNotes().document(docId);

        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Utilities.showToast(NoteDetail.this, "Note telah dihapus");
                    finish();
                }else {
                    Utilities.showToast(NoteDetail.this, "Note gagal untuk dihapus");
                }
            }
        });
    }
}