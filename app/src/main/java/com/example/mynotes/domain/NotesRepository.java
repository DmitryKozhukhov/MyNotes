package com.example.mynotes.domain;

import java.util.List;

public interface NotesRepository {

    List<com.example.mynotes.domain.Note> getNotes();
}
