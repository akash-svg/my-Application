package com.example.akfatraders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Amountdetail extends AppCompatActivity {


    TextView time;
    EditText current;
    EditText previous;
    EditText missing;
    Spinner spinnerGenres;
    Spinner spinner_Genres;
    Spinner store;
    Button btn_send;
    Button btn_total;
    TextView result;
    String r;
    TextView dat;
    String currentDate;
    Double res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amountdetail);

        Calendar calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        //SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");



        dat = findViewById(R.id.date);
        dat.setText(currentDate);
        TextView time = findViewById(R.id.time);
       // time.setText("Time");

        result = findViewById(R.id.result);

        current =findViewById(R.id.current);
        previous =findViewById(R.id.previous);
        missing =findViewById(R.id.missing);

        spinnerGenres = findViewById(R.id.spinnerGenres);
        spinner_Genres = findViewById(R.id.spinner_Genres);
        store = findViewById(R.id.spinnerstore);

        btn_total = findViewById(R.id.btn_total);
        btn_send = findViewById(R.id.btn_send);

        btn_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp = current.getText().toString();
                String temp1 = previous.getText().toString();
                   res = Double.parseDouble(temp1) + Double.parseDouble(temp);
               Double res1 = Double.parseDouble(temp1) - Double.parseDouble(temp);


                result.setText("Total= "+res);

            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s =store.getSelectedItem().toString();
                String temp = current.getText().toString();
                String temp1 = previous.getText().toString();
                String m=missing.getText().toString();
                DatabaseReference dref= FirebaseDatabase.getInstance().getReference("User");
                String id =dref.push().getKey();
                dref.child(id).child("Date").setValue(currentDate);
                dref.child(id).child("Current_Amount").setValue(temp);
                dref.child(id).child("Previous_Amount").setValue(temp1);
                dref.child(id).child("Tottal_Amount").setValue(res);
                dref.child(id).child("Missing_Amount").setValue(m);
            }
        });


    }
}