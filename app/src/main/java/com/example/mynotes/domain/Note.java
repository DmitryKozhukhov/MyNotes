package com.example.mynotes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

import java.util.Date;
import java.util.Objects;

public class Note  implements Parcelable{
    private final String id;

    @DrawableRes
    private final int image;
    private final String title;
    private final String content;
    private final Date createdAt;

    public Note(String id, int image, String title, String content, Date createdAt) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return image == note.image && Objects.equals(id, note.id) && Objects.equals(title, note.title) && Objects.equals(content, note.content) && Objects.equals(createdAt, note.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, title, content, createdAt);
    }

    protected Note(Parcel in) {
        id = in.readString();
        image = in.readInt();
        title = in.readString();
        content = in.readString();
        createdAt = (Date) in.readSerializable();
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

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(image);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeSerializable(createdAt);
    }
}
