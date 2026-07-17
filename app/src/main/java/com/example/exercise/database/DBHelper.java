package com.example.exercise.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Fitness.db";
    public static final int DATABASE_VERSION = 2;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase openDB(){

        return this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUser =
                "CREATE TABLE User(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "email TEXT UNIQUE," +
                        "password TEXT," +
                        "name TEXT," +
                        "gender TEXT," +
                        "goal TEXT," +
                        "age INTEGER," +
                        "height REAL," +
                        "weight REAL," +
                        "bmi REAL," +
                        "bmr REAL," +
                        "isProfileCompleted INTEGER DEFAULT 0" +
                        ")";

        db.execSQL(createUser);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS User");

        onCreate(db);
    }

    public SQLiteDatabase readDB(){
        return this.getReadableDatabase();

    }

    public boolean insertUser(String name,
                              String email,
                              String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("email", email);
        values.put("password", password);

        values.put("name", name);

        values.put("gender", "");
        values.put("goal", "");

        values.put("age", 0);
        values.put("height", 0);
        values.put("weight", 0);

        values.put("bmi", 0);
        values.put("bmr", 0);

        values.put("isProfileCompleted", 0);

        long result = db.insert("User", null, values);

        return result != -1;
    }

    public boolean checkEmail(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM User WHERE email=?",
                new String[]{email});

        boolean exists = cursor.getCount() > 0;

        cursor.close();

        return exists;
    }

    public boolean checkLogin(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM User WHERE email=? AND password=?",
                new String[]{email, password});

        boolean success = cursor.getCount() > 0;

        cursor.close();

        return success;
    }

}