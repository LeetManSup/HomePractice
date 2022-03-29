package com.mirea.lutsko.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle arguments = getIntent().getExtras();
    }

    public void onClick1(View view) {
        long dateInMillis = System.currentTimeMillis();
        String format = "yyyy-MM-dd HH:mm:ss";
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(new Date(dateInMillis));

        TextView textView = findViewById(R.id.textView);
        textView.setText(dateString);
    }

    public void onClick2(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        TextView textView = findViewById(R.id.textView);
        String text = textView.getText().toString();

        intent.putExtra("text", text);

        startActivity(intent);
    }
}