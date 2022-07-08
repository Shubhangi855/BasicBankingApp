package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basicbankingapp.R;

public class MainActivity extends AppCompatActivity {

     SQLiteDatabase mydb;
     public void transactions(View view)
     {
         Intent intent = new Intent(getApplicationContext(),Transactions.class);
         startActivity(intent);
     }
    public void displayUsers(View view)
    {
        Intent intent = new Intent(getApplicationContext(),Users_list.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");
    }

}
