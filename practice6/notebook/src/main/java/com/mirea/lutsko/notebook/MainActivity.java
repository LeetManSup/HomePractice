package com.mirea.lutsko.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String SAVED_NAME = "saved_name";
    final String SAVED_NOTE = "saved_note";

    private EditText nameEditText;
    private TextView noteEditText;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        noteEditText = findViewById(R.id.noteEditText);

        preferences = getPreferences(MODE_PRIVATE);

        String name = preferences.getString(SAVED_NAME, "Name");
        nameEditText.setText(name);

        String note = preferences.getString(SAVED_NOTE, "Note");
        noteEditText.setText(note);
    }

    public void onClickSave(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SAVED_NAME, nameEditText.getText().toString());
        editor.putString(SAVED_NOTE, noteEditText.getText().toString());
        editor.apply();
        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
    }

}