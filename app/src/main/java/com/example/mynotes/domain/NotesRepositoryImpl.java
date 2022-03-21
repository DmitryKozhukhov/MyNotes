package com.example.mynotes.domain;

import android.os.Handler;
import android.os.Looper;

import com.example.mynotes.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NotesRepositoryImpl implements com.example.mynotes.domain.NotesRepository {

    private final Executor executor = Executors.newSingleThreadExecutor();

    private final List<Note> notes = new ArrayList<>();

    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    public NotesRepositoryImpl() {

        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Зарядка", "Сделать 100 присяданий, 100 отжиманий", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Стомотолог", "Записаться к стоматологу", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Английский", "Записаться на курсы английского", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Уборка", "Навести порядок на рабочем столе", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Выступление", "Подготовиться к выступлению в понедельник", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Магазин", "Купить продукты домой", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Собака", "Свозить Бобика к ветеринару", new Date()));
        notes.add(new com.example.mynotes.domain.Note(UUID.randomUUID().toString(), R.drawable.event_note, "Выставка", "Сходить на выставку сибирских пельменей", new Date()));

    }

    public static final NotesRepository INSTANCE = new NotesRepositoryImpl();

    public static NotesRepository getInstance() {
        return INSTANCE;
    }


    @Override
    public void getNotes(Callback<List<Note>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(notes);
                    }
                });
            }
        });
    }

    @Override
    public void add(String title, String content, Callback<Note> callback) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Note note = new Note(UUID.randomUUID().toString(), R.drawable.event_note, title, content, new Date());

                notes.add(note);

                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(note);
                    }
                });
            }
        });

    }

    @Override
    public void delete(Note note, Callback<Void> callback) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                notes.remove(note);

                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(null);
                    }
                });
            }
        });
    }


    @Override
    public Note update(String id, String newTitle, String newContent) {

        Note toChange = null;
        int indexToChange = -1;

        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId().equals(id)) {
                toChange = notes.get(i);
                indexToChange = i;
                break;
            }
        }

        assert toChange != null;
        Note newNote = new Note(toChange.getId(), R.drawable.event_note, newTitle, newContent, toChange.getCreatedAt());

        notes.set(indexToChange, newNote);

        return newNote;
    }
}
