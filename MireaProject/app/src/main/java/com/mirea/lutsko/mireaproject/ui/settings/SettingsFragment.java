package com.mirea.lutsko.mireaproject.ui.settings;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.mirea.lutsko.mireaproject.R;

public class SettingsFragment extends Fragment {
    final String FIRST_PARAMETER = "parameter_1";
    final String SECOND_PARAMETER = "parameter_2";
    final String THIRD_PARAMETER = "parameter_3";

    private EditText nameParTextView;
    private EditText surnameParTextView;
    private EditText ageParTextView;

    private SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        nameParTextView = view.findViewById(R.id.nameParTextView);
        surnameParTextView = view.findViewById(R.id.surnameParTextView);
        ageParTextView = view.findViewById(R.id.ageParTextView);

        view.findViewById(R.id.saveParButton).setOnClickListener(this::onClickSave);

        preferences = getActivity().getPreferences(MODE_PRIVATE);

        return view;
    }

    public void onClickSave(View view) {
        try {
            String param1 = nameParTextView.getText().toString();
            String param2 = surnameParTextView.getText().toString();
            int param3 = Integer.parseInt(ageParTextView.getText().toString());

            SharedPreferences.Editor editor = preferences.edit();

            editor.putString(FIRST_PARAMETER, param1);
            editor.putString(SECOND_PARAMETER, param2);
            editor.putInt(THIRD_PARAMETER, param3);

            Toast.makeText(getActivity(), "Parameters have been saved", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException exception) {
            Toast.makeText(getActivity(), "Error: incorrect input", Toast.LENGTH_SHORT).show();
        }
    }
}