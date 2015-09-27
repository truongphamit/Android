package org.example.truongpq.quicknote.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.example.truongpq.quicknote.model.Note;

import java.util.ArrayList;

/**
 * Created by truongpq on 9/21/15.
 */
public class NoteDAO {
    private NoteDatabaseHelper noteDatabaseHelper;

    public NoteDAO(Context context) {
        noteDatabaseHelper = new NoteDatabaseHelper(context);
    }

    public int insert(Note note) {
        //Open connection to write data
        SQLiteDatabase db = noteDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteDatabaseHelper.KEY_TITLE, note.getTitle());
        contentValues.put(NoteDatabaseHelper.KEY_NOTE, note.getNote());

        long id = db.insert(NoteDatabaseHelper.TABLE_NAME, null, contentValues);
        db.close();

        return (int) id;
    }

    public void delete(int id) {
        SQLiteDatabase db = noteDatabaseHelper.getWritableDatabase();
        db.delete(NoteDatabaseHelper.TABLE_NAME, NoteDatabaseHelper.KEY_ID + "= ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void update(Note note) {
        SQLiteDatabase db = noteDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NoteDatabaseHelper.KEY_TITLE, note.getTitle());
        contentValues.put(NoteDatabaseHelper.KEY_NOTE, note.getNote());
        db.update(NoteDatabaseHelper.TABLE_NAME, contentValues, NoteDatabaseHelper.KEY_ID + " = ?", new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public ArrayList<Note> getList() {
        SQLiteDatabase db = noteDatabaseHelper.getReadableDatabase();
        ArrayList<Note> notes = new ArrayList<>();

        String selectQuerry = "SELECT " +
                NoteDatabaseHelper.KEY_ID + ", " +
                NoteDatabaseHelper.KEY_TITLE + ", " +
                NoteDatabaseHelper.KEY_NOTE +
                " FROM " + NoteDatabaseHelper.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuerry, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(0));
                note.setTitle(cursor.getString(1));
                note.setNote(cursor.getString(2));
                notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return notes;
    }

    public Note findById(int id) {
        SQLiteDatabase db = noteDatabaseHelper.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + NoteDatabaseHelper.TABLE_NAME + " WHERE " + NoteDatabaseHelper.KEY_ID + " = " + id;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Note note = new Note();
        note.setId(c.getInt(0));
        note.setTitle(c.getString(1));
        note.setNote(c.getString(2));

        return note;
    }


}
