package com.mirea.lutsko.mireaproject;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    final String SAVED_TEXT = "saved_text";

    private EditText editText;
    private SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}