package com.example.basicbankingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.basicbankingapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Users_list extends AppCompatActivity {
    ListView listView;
    Database_helper db;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        setTitle("  Users");
        listView = findViewById(R.id.users_list);
        String[] Users = new String[]{"name","email","curr_balance"};
        int[] to = new int[]{R.id.nameView,R.id.emailVIew,R.id.balanceView};
        db = new Database_helper(this);
        Cursor c = db.getinfo();
        Cursor tempc = c;
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.single_user,c,Users,to,0);
        listView.setAdapter(adapter);
        final List<Map<String,String>> users = new ArrayList<Map<String, String>>();
        Map<String,String> temp_user;
        tempc.moveToFirst();
        while(!tempc.isAfterLast())
        {
            @SuppressLint("Range") String name = tempc.getString(tempc.getColumnIndex("name"));
            @SuppressLint("Range") String email = tempc.getString(tempc.getColumnIndex("email"));
            @SuppressLint("Range") int balance = tempc.getInt(tempc.getColumnIndex("curr_balance"));
            temp_user = new HashMap<>();
            temp_user.put("name",name);
            temp_user.put("email",email);
            temp_user.put("curr_balance",String.valueOf(balance));
            users.add(temp_user);
            tempc.moveToNext();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String> info = users.get(position);
                Intent intent = new Intent(getApplicationContext(),to_user.class);
                intent.putExtra("map", (Serializable) info);
                startActivity(intent);
            }
        });
    }
}
