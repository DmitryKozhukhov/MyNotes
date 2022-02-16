package com.example.mynotes.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.R;
import com.example.mynotes.domain.Note;

public class NoteDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_NOTE = "EXTRA_NOTE";

    public static void show(Context context, Note note) {

        Intent intent = new Intent(context, NoteDetailsActivity.class);
        intent.putExtra(EXTRA_NOTE, note);

        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        if (savedInstanceState == null) {

            Note note = getIntent().getParcelableExtra(EXTRA_NOTE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_list, com.example.mynotes.ui.details.NoteDetailsFragment.newInstance(note))
                    .commit();
        }
    }
}