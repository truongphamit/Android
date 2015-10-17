package com.android.truongpq.tet.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.truongpq.tet.models.Sms;
import com.android.truongpq.tet.models.Title;

import java.util.ArrayList;

/**
 * Created by truongpq on 10/8/15.
 */
public class SmsDAO {
    public static final String TABLE_NAME = "tb_chap";

    public static final String KEY_BOOKMARK = "_bookmark";
    public static final String KEY_ID = "_id";
    public static final String KEY_TYPE = "_type";
    public static final String KEY_CONTENT = "_content";

    SQLiteDatabase db;
    TitleHelper helper;

    public SmsDAO(Context context) {
        helper = new TitleHelper(context);
        helper.createDB();
        db = helper.openDB();
    }

    public ArrayList<Sms> getList() {
        ArrayList<Sms> smses = new ArrayList<>();

        String selectQuerry = "SELECT " +
                KEY_BOOKMARK + ", " +
                KEY_ID + ", " +
                KEY_TYPE + ", " +
                KEY_CONTENT +
                " FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuerry, null);

        if (cursor.moveToFirst()) {
            do {
                Sms sms = new Sms();
                sms.setBookmark(cursor.getInt(0));
                sms.setId(cursor.getInt(1));
                sms.setType(cursor.getInt(2));
                sms.setContent(cursor.getString(3));
                smses.add(sms);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return smses;
    }

    public ArrayList<Sms> findByType(int type) {
        ArrayList<Sms> result = new ArrayList<>();
        for (Sms n : getList()) {
            if (n.getType() == type) {
                result.add(n);
            }
        }
        return result;
    }

    public ArrayList<Sms> findByBoorkmark() {
        ArrayList<Sms> result = new ArrayList<>();
        for (Sms n : getList()) {
            if (n.getBookmark() == 1) {
                result.add(n);
            }
        }
        return result;
    }

    public void addBookmark(int id) {
        ContentValues contentValues = new ContentValues();
        Sms sms = findById(id);
        contentValues.put(KEY_BOOKMARK, 1);
        contentValues.put(KEY_TYPE, sms.getType());
        contentValues.put(KEY_CONTENT, sms.getContent());
        db.update(TABLE_NAME, contentValues, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void removeBookmark(int id) {
        ContentValues contentValues = new ContentValues();
        Sms sms = findById(id);
        contentValues.put(KEY_BOOKMARK, 0);
        contentValues.put(KEY_TYPE, sms.getType());
        contentValues.put(KEY_CONTENT, sms.getContent());
        db.update(TABLE_NAME, contentValues, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Sms findById(int id) {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Sms sms = new Sms();
        sms.setBookmark(c.getInt(0));
        sms.setId(c.getInt(1));
        sms.setType(c.getInt(2));
        sms.setContent(c.getString(3));

        return sms;
    }

}
