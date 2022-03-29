package com.mirea.lutsko.intentapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            String text = arguments.get("text").toString();
            textView.setText(text);
        }

        setContentView(textView);
    }
}