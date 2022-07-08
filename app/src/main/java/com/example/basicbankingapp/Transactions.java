package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.basicbankingapp.R;

public class Transactions extends AppCompatActivity {

    Transaction_Database tdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Transactions");
        setContentView(R.layout.activity_transactions);
        ListView listView = findViewById(R.id.tlist);
        String[] Users = new String[]{"from_user","to_user","amount","status"};
        int[] to = new int[]{R.id.fromView,R.id.toVIew,R.id.amountView,R.id.statusView};
        tdb = new Transaction_Database(this);
        Cursor c = tdb.getinfo();
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.single_transaction,c,Users,to,0);
        listView.setAdapter(simpleCursorAdapter);
    }
}
