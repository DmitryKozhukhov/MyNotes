package com.example.mynotes.ui.list;

import com.example.mynotes.domain.Note;
import com.example.mynotes.domain.NotesRepository;

import java.util.List;

public class NotesListPresenter {

    private final NotesListView view;
    private final NotesRepository repository;

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes(){
        List<Note> notes = repository.getNotes();

        view.showNotes(notes);
    }
}
