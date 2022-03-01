package ru.mirea.lutsko.task6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView myTextView = (TextView) findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        setContentView(R.layout.activity_main);
    }
}