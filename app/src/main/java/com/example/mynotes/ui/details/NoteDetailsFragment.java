package com.example.mynotes.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.mynotes.R;
import com.example.mynotes.domain.Note;
import com.example.mynotes.ui.list.NotesListFragment;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eventNote = view.findViewById(R.id.event_note);

        description = view.findViewById(R.id.description);

        dateOfCreation = view.findViewById(R.id.dateOfCreation);

        view.findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getParentFragmentManager()
                        .popBackStack();
            }
        });


        Bundle arguments = getArguments();

        if (arguments != null && arguments.containsKey(ARG_NOTE)){
            Note note = arguments.getParcelable(ARG_NOTE);

            updateNote(note);
        }
    }


    private void updateNote(Note note){

        eventNote.setImageResource(note.getImage());

        description.setText(note.getDescription());

        dateOfCreation.setText(note.getDateOfCreation());
    }
}
