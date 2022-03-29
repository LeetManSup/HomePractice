package com.mirea.lutsko.mireaproject;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.mirea.lutsko.mireaproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.calculateFragment, R.id.webViewFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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