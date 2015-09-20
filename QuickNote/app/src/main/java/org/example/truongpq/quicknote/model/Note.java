package org.example.truongpq.quicknote.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by truongpq on 9/13/15.
 */
public class Note implements Parcelable {
    private String title;
    private String note;

    public Note() {
    }

    public Note(String title, String note) {
        this.title = title;
        this.note = note;
    }

    protected Note(Parcel in) {
        title = in.readString();
        note = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(note);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
