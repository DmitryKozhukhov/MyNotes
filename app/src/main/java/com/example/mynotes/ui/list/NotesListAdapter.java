package com.example.mynotes.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.R;
import com.example.mynotes.domain.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NoteViewHolder> {

    private final Fragment fragment;

    public NotesListAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public OnNoteClicked getOnNoteClicked() {
        return onNoteClicked;
    }

    public void setOnNoteClicked(OnNoteClicked onNoteClicked) {
        this.onNoteClicked = onNoteClicked;
    }

    interface OnNoteClicked {
        void onNoteClicked(Note note);

        void onNoteLongClicked(Note note, int position);
    }

    private final SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

    private final List<Note> data = new ArrayList<>();

    public void setData(Collection<Note> toSet) {
        data.clear();
        data.addAll(toSet);
    }

    public int addItem(Note toAdd) {
        data.add(toAdd);

        return data.size() - 1;
    }


    public void removeItem(int selectedNoteIndex) {
        data.remove(selectedNoteIndex);
    }

    public void updateItem(Note note, int selectedNoteIndex) {
        data.set(selectedNoteIndex, note);
    }

    private OnNoteClicked onNoteClicked;

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note item = data.get(position);

        holder.name.setText(item.getTitle());
        holder.content.setText(item.getContent());
        holder.image.setImageResource(R.drawable.event_note);
        holder.date.setText(format.format(item.getCreatedAt()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView content;
        TextView date;
        ImageView image;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            View card = itemView.findViewById(R.id.card);

            fragment.registerForContextMenu(card);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getOnNoteClicked() != null) {

                        int clickedAt = getAdapterPosition();

                        getOnNoteClicked().onNoteClicked(data.get(clickedAt));
                    }

                }
            });

            card.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    if (getOnNoteClicked() != null) {

                        int clickedAt = getAdapterPosition();

                        getOnNoteClicked().onNoteLongClicked(data.get(clickedAt), clickedAt);
                    }

                    view.showContextMenu();

                    return true;
                }
            });

            name = itemView.findViewById(R.id.name);
            content = itemView.findViewById(R.id.content);
            date = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.image);
        }
    }
}
