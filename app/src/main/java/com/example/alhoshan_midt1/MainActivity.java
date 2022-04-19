package com.example.alhoshan_midt1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView temperature,humidity;
    JSONObject jsonObj;
    //NOTE I TRIED MAKING THIS LINK WORK BUT IT WOULDNT IT KEEPS GIVING ME AN ERROR BUT ALL THE SYNTAX IS CORRECT
    String weatherWebserviceURL = "http://api.openweathermap.org/data/2.5/weather?q=london&appid=3f3d1f63011f9fa0cfd13c8748c3776d&units=metric";
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bttnDate = findViewById(R.id.bttnDate);
        TextView dateText = findViewById(R.id.dateText);
        TextView cityChoice = findViewById(R.id.city);
        temperature = (TextView) findViewById(R.id.temp);
        humidity = findViewById(R.id.humidity);
        Button bttnChoice = findViewById(R.id.choose);
        weather(weatherWebserviceURL);
        bttnChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner = findViewById(R.id.spinner);
                String city = spinner.getSelectedItem().toString();
                cityChoice.setText(city);
                //NOTE I TRIED MAKING THIS LINK WORK BUT IT WOULDNT IT KEEPS GIVING ME AN ERROR BUT ALL THE SYNTAX IS CORRECT
                weatherWebserviceURL = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=3f3d1f63011f9fa0cfd13c8748c3776d&units=metric";
                weather(weatherWebserviceURL);
            }
        });
        Button main2 = findViewById(R.id.main2);
        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
        Calendar c = Calendar.getInstance();
        DateFormat fmtDate = DateFormat.getDateInstance();
        DatePickerDialog.OnDateSetListener d = new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        c.set(Calendar.YEAR, year);
                        c.set(Calendar.MONTH, monthOfYear);
                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        dateText.setText("Your picked date  is "+
                                fmtDate.format(c.getTime()));
                        Log.d("Date","Successful");
                    }
                };
        bttnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, d,
                        c.get(java.util.Calendar.YEAR),
                        c.get(java.util.Calendar.MONTH),
                        c.get(java.util.Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    public void weather(String url) {
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Saud-Response","Response Retrieved");
                        Log.d("Saud-JSON",response.toString());
                        try {
                            JSONObject jsonMain = response.getJSONObject("main");
                            double temp = jsonMain.getDouble("temp");
                            Log.d("Saud-temp=","temp="+String.valueOf(temp));
                            temperature.setText(String.valueOf(temp));

                            double hum = jsonMain.getDouble("humidity");
                            Log.d("Saud-humidity=","humidity="+String.valueOf(hum));
                            humidity.setText("Humidity: "+String.valueOf(hum));

                        }catch (JSONException e){
                            e.printStackTrace();
                            Log.e("JSON Error",e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Saud","Error retrieving URL");
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }
}