package com.example.alhoshan_midt1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity3 extends AppCompatActivity {
    final DatabaseHelper MyDB = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText id = findViewById(R.id.id2);
        Button view = findViewById(R.id.view);
        Button delete = findViewById(R.id.delete);
        Button main2 = findViewById(R.id.act2);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = MyDB.ViewUsers();
                StringBuffer buffer = new StringBuffer();
                while(cur.moveToNext()){
                    buffer.append("id: "+cur.getString(0)+ "\n");
                    buffer.append("Name: "+cur.getString(1)+ "\n");
                    buffer.append("Surname: "+cur.getString(2)+ "\n");
                    buffer.append("National ID: "+cur.getString(3)+ "\n\n");
                }
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(MainActivity3.this);
                builder.setCancelable(true);
                builder.setTitle("All Users");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_val = id.getText().toString();
                MyDB.DeleteUsers(id_val);
                Log.d("Delete","Deleted Successfully");
                Toasty.success(getBaseContext(), "Values Deleted", Toast.LENGTH_SHORT, true).show();
            }
        });
        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, MainActivity2.class));
            }
        });
    }
}