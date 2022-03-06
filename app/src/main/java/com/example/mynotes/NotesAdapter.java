package com.example.mynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private List<Note> notes;
    private onNoteClickListener onNoteClickListener;

    interface  onNoteClickListener {

        void onNoteClick (int position);
        void onLongClick (int position);
    }

    public void setOnNoteClickListener(NotesAdapter.onNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }


    public NotesAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());
        holder.dayOfWeek.setText(note.getDayAsString(position));
        int idColor;
        int priorety = note.getPriorety();
        switch (priorety) {
            case 1:
                idColor = holder.itemView.getResources().getColor(android.R.color.holo_red_dark);
                break;
            case 2:
                idColor = holder.itemView.getResources().getColor(android.R.color.holo_orange_light);
                break;
            default:
                idColor = holder.itemView.getResources().getColor(android.R.color.holo_green_dark);
        }
        holder.title.setBackgroundColor(idColor);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private TextView dayOfWeek;
        private TextView priorety;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            description = itemView.findViewById(R.id.textViewDescription);
            dayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ( onNoteClickListener != null) {
                        onNoteClickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if ( onNoteClickListener != null) {
                        onNoteClickListener.onLongClick(getAdapterPosition());
                    }
                    return  true;
                }
            });
        }
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();;
    }

    public  List<Note> getNotes() {
        return notes;
    }
}
