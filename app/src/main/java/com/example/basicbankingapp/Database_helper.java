package com.example.basicbankingapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database_helper extends SQLiteOpenHelper {

    public Database_helper(Context context) {
        super(context,"UserData.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL("CREATE TABLE IF NOT EXISTS customers( _id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, email VARCHAR , curr_balance INTEGER)");
        mydb.execSQL("INSERT INTO customers (name,email,curr_balance) VALUES ('Ram','Ram@gmail.com',2000)");
        mydb.execSQL("INSERT INTO customers (name,email,curr_balance) VALUES ('Ravi','Ravi@gmail.com',5000)");
        mydb.execSQL("INSERT INTO customers (name,email,curr_balance) VALUES ('Abhi','Abhi@gmail.com',4000)");
        mydb.execSQL("INSERT INTO customers (name,email,curr_balance) VALUES ('Miths','Miths@gmail.com',3000)");
        mydb.execSQL("INSERT INTO customers (name,email,curr_balance) VALUES ('Sri','Sri@gmail.com',3500)");
        mydb.execSQL("INSERT INTO customers (name,email,curr_balance) VALUES ('Shyam','Shyam@gmail.com',6000)");
        mydb.execSQL("INSERT INTO customers (name,email,curr_balance) VALUES ('Anil','Anil@gmail.com',3000)");
        mydb.execSQL("INSERT INTO customers (name,email,curr_balance) VALUES ('Arjun','Arjun@gmail.com',3000)");
        mydb.execSQL("INSERT INTO customers (name,email,curr_balance) VALUES ('Vish','Vish@gmail.com',3000)");
        mydb.execSQL("INSERT INTO customers (name,email,curr_balance) VALUES ('lalit','llalit@gmail.com',3500)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int oldVersion, int newVersion) {
        mydb.execSQL("Drop table if exists customers");
    }
    public Cursor getinfo()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from customers",null);
        return cursor;
    }

    public boolean update(String toemail,String fromemail,int amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update customers set curr_balance=curr_balance + "+amount+" where email=?",new String[] {toemail});
        db.execSQL("update customers set curr_balance=curr_balance - "+amount+" where email=?",new String[] {fromemail});
        return true;
    }
    public Cursor getUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from customers where email=="+email;
        Cursor cursor = db.rawQuery("Select * from customers where email !=?",new String[]{email});
        return cursor;
    }
}
