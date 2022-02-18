package com.example.mynotes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

public class Note implements Parcelable {
    private final String id;

    @DrawableRes
    private final int image;

    private final String name;
    private final String description;
    private final String dateOfCreation;

    public Note(String id, int image, String name, String description, String dateOfCreation) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.dateOfCreation = dateOfCreation;
    }


    protected Note(Parcel in) {
        id = in.readString();
        image = in.readInt();
        name = in.readString();
        description = in.readString();
        dateOfCreation = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(image);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(dateOfCreation);
    }
}
