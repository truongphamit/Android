package com.android.truongpq.tet.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TitleHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_sms_love.db";

    private String path;
    private Context context;

    public TitleHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        this.path = context.getFilesDir().getParent() + "/databases/" + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean checkDB() {
        SQLiteDatabase db = null;
        try {
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (db != null) {
            db.close();
            return true;
        }
        return false;
    }

    public SQLiteDatabase openDB(){
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);

    }

    public void createDB() {
            boolean check = checkDB();
            if(check){
                Log.d("KetNoi", "Máy đã có database");
            }else{
                Log.d("KetNoi", "Máy chưa có database tiến hành copy dữ liệu");
                this.getWritableDatabase();
                copyDB();
            }
    }

    public void copyDB() {
        try {
            InputStream is =  context.getAssets().open(DB_NAME);
            OutputStream os = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int lenght;
            while((lenght = is.read(buffer)) > 0){
                os.write(buffer, 0, lenght);
            }

            os.flush();
            os.close();
            is.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
