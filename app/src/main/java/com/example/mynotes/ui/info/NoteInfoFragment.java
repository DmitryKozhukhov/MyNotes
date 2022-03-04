package com.example.mynotes.ui.info;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.mynotes.R;
import com.example.mynotes.ui.NavDrawable;

public class NoteInfoFragment extends Fragment {

    public NoteInfoFragment() {
        super(R.layout.fragment_note_info);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (requireActivity() instanceof NavDrawable) {
            ((NavDrawable) requireActivity()).setAppBar(toolbar);
        }

        super.onViewCreated(view, savedInstanceState);
    }
}
