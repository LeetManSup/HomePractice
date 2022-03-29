package com.mirea.lutsko.mireaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Fragment fragment1, fragment2;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment2 = new CalculateFragment();
        fragment1 = new WebViewFragment();
    }
    public void onClick(View view) {
        fragmentManager = getSupportFragmentManager();
        switch (view.getId()){
            case R.id.btnFragment1:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                        fragment1).commit();
                break;
            case R.id.btnFragment2:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                        fragment2).commit();
                break;
            default:
                break;
        }
    }

    public void onClick1(View view) {
        EditText editText = findViewById(R.id.result);
        editText.setText("");
    }

    public void onClick2(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        if (string.length() > 0) {
            string = string.substring(0, string.length() - 1);
        }

        editText.setText(string);
    }

    public void onClick3(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        try {
            float res = Float.parseFloat(string);
            res = res / 100;

            string = "" + res;
            editText.setText(string);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void onClick4(View view) {
        try {
            EditText editText = findViewById(R.id.result);
            String string = editText.getText().toString();
            char a = string.charAt(string.length() - 1);

            if (a != '*' && a != '/' && a != '+' && a != '-' && a != '.') {
                string = string + '/';
                editText.setText(string);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick5(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        string = string + '7';

        editText.setText(string);
    }

    public void onClick6(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        string = string + '8';

        editText.setText(string);
    }

    public void onClick7(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        string = string + '9';

        editText.setText(string);
    }

    public void onClick8(View view) {
        try {
            EditText editText = findViewById(R.id.result);
            String string = editText.getText().toString();
            char a = string.charAt(string.length() - 1);

            if (a != '*' && a != '/' && a != '+' && a != '-' && a != '.') {
                string = string + '*';
                editText.setText(string);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick9(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        string = string + '4';

        editText.setText(string);
    }

    public void onClick10(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        string = string + '5';

        editText.setText(string);
    }

    public void onClick11(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        string = string + '6';

        editText.setText(string);
    }

    public void onClick12(View view) {
        try {
            EditText editText = findViewById(R.id.result);
            String string = editText.getText().toString();
            char a = string.charAt(string.length() - 1);

            if (a != '*' && a != '/' && a != '+' && a != '-' && a != '.') {
                string = string + '-';
                editText.setText(string);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick13(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        string = string + '1';

        editText.setText(string);
    }

    public void onClick14(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        string = string + '2';

        editText.setText(string);
    }

    public void onClick15(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        string = string + '3';

        editText.setText(string);
    }

    public void onClick16(View view) {
        try {
            EditText editText = findViewById(R.id.result);
            String string = editText.getText().toString();
            char a = string.charAt(string.length() - 1);

            if (a != '*' && a != '/' && a != '+' && a != '-' && a != '.') {
                string = string + '+';
                editText.setText(string);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick17(View view) {
        try {
            EditText editText = findViewById(R.id.result);
            String string = editText.getText().toString();
            char a = string.charAt(string.length() - 1);

            if (a != '*' && a != '/' && a != '+' && a != '-' && a != '.') {
                string = string + '.';
                editText.setText(string);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick18(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        string = string + '0';

        editText.setText(string);
    }

    public void onClick19(View view) {
        try {
            EditText editText = findViewById(R.id.result);
            String string = editText.getText().toString();
            char a = string.charAt(string.length() - 1);

            if (a != '*' && a != '/' && a != '+' && a != '-' && a != '.') {
                float res = Float.parseFloat(string);

                string = "" + Math.sqrt(res);
                editText.setText(string);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick20(View view) {
        EditText editText = findViewById(R.id.result);
        String string = editText.getText().toString();

        try {
            float res = Float.parseFloat(string);

            string = "" + res;
            editText.setText(string);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}