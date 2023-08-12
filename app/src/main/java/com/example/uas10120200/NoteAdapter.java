package com.example.uas10120200;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteViewHolder> {

    Context context;

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options, Context context) {
        super(options);
        this.context = context;
    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        holder.noteTextView.setText(note.title);
        holder.detailTextView.setText(note.detail);
        holder.timeTextView.setText(Utilities.timestampToString(note.timestamp));
        holder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(context,NoteDetail.class);
            intent.putExtra("title",note.title);
            intent.putExtra("detail",note.detail);
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId",docId);
            context.startActivity(intent);

        });

    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item,parent,false);
        return new NoteViewHolder(view);
    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView noteTextView,detailTextView,timeTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTextView = itemView.findViewById(R.id.note_title_text_view);
            detailTextView = itemView.findViewById(R.id.note_detail_text_view);
            timeTextView = itemView.findViewById(R.id.note_time_text_view);
        }
    }

}
