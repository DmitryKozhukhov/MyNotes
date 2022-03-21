package com.example.mynotes.domain;

public interface Callback<T> {

    void onSuccess(T data);
}
