package com.example.mynotes.ui.list;

import com.example.mynotes.domain.Note;

import java.util.List;

public interface NotesListView {

    void showNotes(List<Note> notes);

    void addNote(Note note);

    void removeNote(Note note, int index);

    void showProgress();

    void hideProgress();

}
