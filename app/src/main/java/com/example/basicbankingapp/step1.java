package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

public class step1 extends AppCompatActivity {
    Map<String,String> map;
    final List<Map<String,String>> users = new ArrayList<Map<String, String>>();
    ListView listView;
    Database_helper db;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),to_user.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);
        setTitle("To");
        final Intent intent = getIntent();
        map = (Map<String, String>) intent.getSerializableExtra("from_user");
        String email1 = map.get("email");
        listView = findViewById(R.id.users_list);
        String[] Users = new String[]{"name","email","curr_balance"};
        int[] to = new int[]{R.id.nameView,R.id.emailVIew,R.id.balanceView};
        db = new Database_helper(this);
        Cursor c = db.getUser(email1);
        Cursor tempc = c;
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.single_user,c,Users,to,0);
        listView.setAdapter(adapter);
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
                Intent intent1 = new Intent(getApplicationContext(),TransferMoney.class);
                Map<String,String> info = users.get(position);
                intent1.putExtra("from",(Serializable) map);
                intent1.putExtra("map", (Serializable) info);
                startActivity(intent1);
            }
        });
    }
}
