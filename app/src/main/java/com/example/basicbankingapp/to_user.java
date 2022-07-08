package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.basicbankingapp.R;

import java.io.Serializable;
import java.util.Map;

public class to_user extends AppCompatActivity {

    TextView nameText;
    TextView emailText;
    TextView balanceText;
    Map<String,String> map;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),Users_list.class);
        startActivity(intent);
        finish();
    }

    public void gotonext(View view)
    {
        Intent intent = new Intent(getApplicationContext(),step1.class);
        intent.putExtra("from_user",(Serializable) map);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_user);
        setTitle("Profile");
        Intent intent = getIntent();
        map = (Map<String, String>) intent.getSerializableExtra("map");
        nameText = (TextView) findViewById(R.id.nameText);
        emailText = (TextView) findViewById(R.id.emailText);
        balanceText = (TextView) findViewById(R.id.balanceText);
        nameText.setText("Name : "+map.get("name"));
        emailText.setText("Email : "+map.get("email"));
        balanceText.setText("Balance : "+map.get("curr_balance"));
    }
}
