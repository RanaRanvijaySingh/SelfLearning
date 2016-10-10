package com.selflearning.databaseupgradedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static boolean isDBUpgraded = true;
    private static final String DATABASE_NAME = "DemoDatabase.db";
    private static final int DATABASE_VERSION = isDBUpgraded ? 2 : 1;
    private final Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(mContext, "onCreate", Toast.LENGTH_SHORT).show();
        if (isDBUpgraded) {
            db.execSQL("create table users(id integer primary key, name text, number text, phone text)");
        } else {
            db.execSQL("create table users(id integer primary key, name text, number text)");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(mContext
                , "Drop table", Toast.LENGTH_SHORT).show();
        if (oldVersion < newVersion) {
            db.execSQL("alter table users add column phone text default 0");
        }
    }

    public boolean insertUser(String name, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("number", number);
        db.insert("users", null, contentValues);
        return true;
    }

    public boolean insertUser(String name, String number, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("number", number);
        contentValues.put("phone", phone);
        db.insert("users", null, contentValues);
        return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from users", null);
        return res;
    }

    public void deleteAllRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from users");
    }
}
