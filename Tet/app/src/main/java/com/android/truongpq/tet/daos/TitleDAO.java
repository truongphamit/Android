package com.android.truongpq.tet.daos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.truongpq.tet.models.Title;

import java.util.ArrayList;

/**
 * Created by truongpq on 10/7/15.
 */
public class TitleDAO {
    public static final String TABLE_NAME = "tb_cat";

    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "name";

    SQLiteDatabase db;
    TitleHelper helper;

    public TitleDAO(Context context) {
        helper = new TitleHelper(context);
        helper.createDB();
        db = helper.openDB();
    }

    public ArrayList<Title> getList() {
        ArrayList<Title> notes = new ArrayList<>();

        String selectQuerry = "SELECT " +
                KEY_ID + ", " +
                KEY_TITLE +
                " FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuerry, null);

        if (cursor.moveToFirst()) {
            do {
                Title title = new Title();
                title.setId(cursor.getInt(0));
                title.setTitle(cursor.getString(1));
                notes.add(title);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return notes;
    }
}
