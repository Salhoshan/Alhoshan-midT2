package com.example.alhoshan_midt1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Users.db";
    private static final String TABLE_NAME = "User";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_SUR = "Surname";
    private static final String COLUMN_NID = "National";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " +TABLE_NAME +"("+
                        COLUMN_ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME + " TEXT NOT NULL," +
                        COLUMN_SUR + " TEXT NOT NULL," +
                        COLUMN_NID + " INTEGER NOT NULL);"
        );
    }
    public void AddUser (String id, String name, String surname, String nID){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SUR, surname);
        values.put(COLUMN_NID, nID);
        db.insert(TABLE_NAME, null, values);
    }
    public Cursor getSpecificResult(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME+ " WHERE ID = ?", new String[]{id});
        if (data != null)
            data.moveToFirst();
        return data;
    }
    public Cursor ViewUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor x = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return x;
    }
    public Integer DeleteUsers(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
