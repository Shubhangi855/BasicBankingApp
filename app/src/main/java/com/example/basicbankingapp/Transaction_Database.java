package com.example.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Transaction_Database extends SQLiteOpenHelper {
    public Transaction_Database(Context context) {
        super(context,"Transaction.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists transactions( _id integer primary key autoincrement, from_user varchar, to_user varchar, amount integer, status varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists transactions");
    }
    public Cursor getinfo()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from transactions",null);
        return cursor;
    }

    public void insertdata(String from_user, String to_user, int amount, String status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("from_user","From : "+from_user);
        values.put("to_user","To : "+to_user);
        values.put("amount",amount);
        values.put("status",status);
        db.insert("transactions",null,values);
    }
}
