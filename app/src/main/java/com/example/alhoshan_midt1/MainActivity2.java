package com.example.alhoshan_midt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity2 extends AppCompatActivity {
    final DatabaseHelper MyDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText id = findViewById(R.id.id);
        EditText name = findViewById(R.id.name);
        EditText sur = findViewById(R.id.surname);
        EditText nID = findViewById(R.id.nID);
        Button insert = findViewById(R.id.insert);
        Button main1 = findViewById(R.id.main1);
        Button main3 = findViewById(R.id.main3);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_val = id.getText().toString();
                String name_val = name.getText().toString();
                String sur_val = sur.getText().toString();
                String nID_val = nID.getText().toString();
                MyDB.AddUser(id_val, name_val, sur_val, nID_val);
                Log.d("Insertion","Added Successfully");
                Toasty.success(getBaseContext(), "Inserted Successfully", Toast.LENGTH_SHORT, true).show();
            }
        });
        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });
        main3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        });

    }
}