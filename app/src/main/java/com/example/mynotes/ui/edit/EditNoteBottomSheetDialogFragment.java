package com.example.mynotes.ui.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mynotes.R;
import com.example.mynotes.domain.Note;
import com.example.mynotes.domain.NotesRepository;
import com.example.mynotes.domain.NotesRepositoryImpl;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class EditNoteBottomSheetDialogFragment extends BottomSheetDialogFragment {

    public static final String ARG_NOTE = "ARG_NOTE";
    public static final String KEY_REQUEST = "KEY_REQUEST";

    private final NotesRepository repository = NotesRepositoryImpl.INSTANCE;

    public static EditNoteBottomSheetDialogFragment newInstance(Note note) {
        
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        
        EditNoteBottomSheetDialogFragment fragment = new EditNoteBottomSheetDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_note_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Note note = requireArguments().getParcelable(ARG_NOTE);

        EditText title = view.findViewById(R.id.edit_title);
        title.setText(note.getTitle());

        EditText content = view.findViewById(R.id.edit_content);
        content.setText(note.getContent());

        view.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Note updatedNote = repository.update(note.getId(), title.getText().toString(), content.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putParcelable(ARG_NOTE, updatedNote);

                getParentFragmentManager()
                        .setFragmentResult(KEY_REQUEST, bundle);

                dismiss();
            }
        });
    }
}
