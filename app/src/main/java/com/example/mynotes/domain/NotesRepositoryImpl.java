package com.example.mynotes.domain;

import android.graphics.BitmapRegionDecoder;

import com.example.mynotes.R;

import java.util.ArrayList;
import java.util.Date;
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

        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Зарядка", "Сделать 100 присяданий, 100 отжиманий", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Стомотолог", "Записаться к стоматологу", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Английский", "Записаться на курсы английского", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Уборка", "Навести порядок на рабочем столе", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Выступление", "Подготовиться к выступлению в понедельник", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Магазин", "Купить продукты домой", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Собака", "Свозить Бобика к ветеринару", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Выставка", "Сходить на выставку сибирских пельменей", new Date()));

        for (int i = 0; i < 1000; i++) {
            notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Выставка", "Сходить на выставку сибирских пельменей", new Date()));
        }

        return notes;
    }
}
