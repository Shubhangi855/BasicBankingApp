package com.example.basicbankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicbankingapp.R;

import java.util.Map;

public class TransferMoney extends AppCompatActivity {
    Map<String,String> map;
    Map<String,String> from;
    TextView fromText;
    TextView toText;
    EditText moneyInput;
    Database_helper db;
    Transaction_Database tdb;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),Users_list.class);
        startActivity(intent);
        finish();
    }

    public void transfer(View view)
    {
        String value=moneyInput.getText().toString();
        if(value.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please Enter Valid Amount",Toast.LENGTH_SHORT).show();
        }
        else {
            int enteredAmount = Integer.parseInt(value);
            int fromBalance = Integer.parseInt(from.get("curr_balance"));
            int toBalance = Integer.parseInt(map.get("curr_balance"));
            if(enteredAmount>fromBalance)
            {
                Toast.makeText(getApplicationContext(),"Insufficient Balance/Funds",Toast.LENGTH_SHORT).show();
            }
            else{
                    db = new Database_helper(this);
                    boolean res = db.update(map.get("email"),from.get("email"),enteredAmount);
                    if(res)
                    {
                        tdb = new Transaction_Database(this);
                        tdb.insertdata(from.get("name"),map.get("name"),enteredAmount,"Success");
                        new AlertDialog.Builder(this)
                                .setIcon(R.drawable.ic_done_all_black_24dp)
                                .setTitle(" Message")
                                .setMessage("\nTransaction Successful")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        onBackPressed();
                                    }
                                })
                                .show();
                    }
            }
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);
        setTitle("Review");
        Intent intent = getIntent();
        map = (Map<String, String>) intent.getSerializableExtra("map");
        from = (Map<String, String>) intent.getSerializableExtra("from");
        fromText = findViewById(R.id.fromText);
        toText = findViewById(R.id.toText);
        moneyInput = findViewById(R.id.moneyInput);
        fromText.setText("From: "+from.get("name"));
        toText.setText("To: "+map.get("name"));
    }
}
