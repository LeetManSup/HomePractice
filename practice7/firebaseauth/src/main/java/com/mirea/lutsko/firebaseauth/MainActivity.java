package com.mirea.lutsko.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView statusTextView;
    private FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        statusTextView = findViewById(R.id.infoTextView);

        findViewById(R.id.signInButton).setOnClickListener(this);
        findViewById(R.id.newAccButton).setOnClickListener(this);
        findViewById(R.id.signOutButton).setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            statusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            findViewById(R.id.emailEditText).setVisibility(View.GONE);
            findViewById(R.id.passwordEditText).setVisibility(View.GONE);
            findViewById(R.id.signInButton).setVisibility(View.GONE);
            findViewById(R.id.newAccButton).setVisibility(View.GONE);
            findViewById(R.id.signOutButton).setVisibility(View.VISIBLE);

        } else {
            statusTextView.setText(R.string.signed_out);
            findViewById(R.id.passwordEditText).setVisibility(View.VISIBLE);
            findViewById(R.id.emailEditText).setVisibility(View.VISIBLE);
            findViewById(R.id.signInButton).setVisibility(View.VISIBLE);
            findViewById(R.id.newAccButton).setVisibility(View.VISIBLE);
            findViewById(R.id.signOutButton).setVisibility(View.VISIBLE);
        }
    }

    private boolean validateForm() {
        boolean valid = true;
        String email = emailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Required.");
            valid = false;
        } else {
            emailEditText.setError(null);
        }
        String password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Required.");
            valid = false;
        } else {
            passwordEditText.setError(null);
        }
        return valid;
    }

    private void createAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                    if (!task.isSuccessful()) {
                        statusTextView.setText(R.string.auth_failed);
                    }
                });
    }

    private void signOut() {
        auth.signOut();
        updateUI(null);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.newAccButton) {
            createAccount(emailEditText.getText().toString(),
                    passwordEditText.getText().toString());
        } else if (i == R.id.signInButton) {
            signIn(emailEditText.getText().toString(),
                    passwordEditText.getText().toString());
        } else if (i == R.id.signOutButton){
            signOut();
        }
    }
}