package com.example.mynotes.ui.list;

import com.example.mynotes.domain.Callback;
import com.example.mynotes.domain.Note;
import com.example.mynotes.domain.NotesRepository;

import java.util.List;

public class NotesListPresenter {

    private final NotesListView view;
    private final NotesRepository repository;

    public Note getSelectedNote() {
        return selectedNote;
    }

    public void setSelectedNote(Note selectedNote) {
        this.selectedNote = selectedNote;
    }

    public int getSelectedNoteIndex() {
        return selectedNoteIndex;
    }

    public void setSelectedNoteIndex(int selectedNoteIndex) {
        this.selectedNoteIndex = selectedNoteIndex;
    }

    private Note selectedNote;
    private int selectedNoteIndex;

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes() {

        view.showProgress();

        repository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> data) {
                view.showNotes(data);
                view.hideProgress();
            }
        });

    }

    public void addItem() {

        view.showProgress();

        repository.add("Новая заметка", "Создана новая заметка", new Callback<Note>() {
            @Override
            public void onSuccess(Note data) {

                view.hideProgress();

                view.addNote(data);
            }
        });

    }

    public void deleteItem() {

        view.showProgress();

        repository.delete(selectedNote, new Callback<Void>() {
            @Override
            public void onSuccess(Void data) {

                view.hideProgress();

                view.removeNote(selectedNote, selectedNoteIndex);
            }
        });

    }
}
