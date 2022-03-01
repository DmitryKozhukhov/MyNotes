package com.example.mynotes.ui.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.mynotes.R;
import com.example.mynotes.domain.Note;
import com.example.mynotes.ui.NavDrawable;

public class NoteDetailsFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";

    public static NoteDetailsFragment newInstance(Note note) {

        Bundle args = new Bundle();

        args.putParcelable(ARG_NOTE, note);

        NoteDetailsFragment fragment = new NoteDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ImageView eventNote;
    private TextView description;
    private TextView dateOfCreation;

    public NoteDetailsFragment() {
        super(R.layout.fragment_note_details);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (requireActivity() instanceof NavDrawable) {
            ((NavDrawable) requireActivity()).setAppBar(toolbar);
        }
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_share:
                        Toast.makeText(requireContext(), "Share", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_add_photo:
                        Toast.makeText(requireContext(), "Add a photo", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });

        eventNote = view.findViewById(R.id.event_note);

        description = view.findViewById(R.id.description);

        dateOfCreation = view.findViewById(R.id.dateOfCreation);

        Bundle arguments = getArguments();

        if (arguments != null && arguments.containsKey(ARG_NOTE)) {
            Note note = arguments.getParcelable(ARG_NOTE);

            updateNote(note);
        }
    }

    private void updateNote(Note note) {

        eventNote.setImageResource(note.getImage());

        description.setText(note.getDescription());

        dateOfCreation.setText(note.getDateOfCreation());
    }
}
