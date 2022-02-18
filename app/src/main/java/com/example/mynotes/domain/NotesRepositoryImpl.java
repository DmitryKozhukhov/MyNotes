package com.example.mynotes.domain;

import com.example.mynotes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotesRepositoryImpl implements com.example.mynotes.domain.NotesRepository {

    private static final NotesRepositoryImpl INSTANCE = new NotesRepositoryImpl();

    public static NotesRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<com.example.mynotes.domain.Note> getNotes() {
        ArrayList<com.example.mynotes.domain.Note> notes = new ArrayList<>();

        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Зарядка", "Сделать 100 присяданий, 100 отжиманий", "Дата создания 16.02.2022"));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Стомотолог", "Записаться к стоматологу", "Дата создания 16.02.2022"));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Английский", "Записаться на курсы английского", "Дата создания 16.02.2022"));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Уборка", "Навести порядок на рабочем столе", "Дата создания 16.02.2022"));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Выступление", "Подготовиться к выступлению в понедельник", "Дата создания 16.02.2022"));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Магазин", "Купить продукты домой", "Дата создания 16.02.2022"));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Собака", "Свозить Бобика к ветеринару", "Дата создания 16.02.2022"));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Выставка", "Сходить на выставку сибирских пельменей", "Дата создания 16.02.2022"));

        return notes;
    }
}
