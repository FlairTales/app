package com.example.android.homepage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SignIn";

    private EditText mEmailField;
    private EditText mPasswordField;

    // [START declare_auth]
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEmailField = findViewById(R.id.usernameMethod);
        mPasswordField = findViewById(R.id.passwordMethod);
        findViewById(R.id.signInMetod).setOnClickListener(this);
        findViewById(R.id.signUpMethod).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    // [END on_start_check_user]

    private void updateUI(FirebaseUser user) {
        // hideProgressDialog();
        if (user != null) {
            //findViewById(R.id.signInLayout).setVisibility(View.GONE);
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            findViewById(R.id.signInLayout).setVisibility(View.VISIBLE);
        }
    }
    private void signInEmail(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateFormEmail()) {
            return;
        }

        // showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:Auth Failed !");
                        }
                        // hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }
    private boolean validateFormEmail() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.signUpMethod) {
            Intent intentEmail = new Intent(SignInActivity.this, EmailPasswordActivity.class);
            startActivity(intentEmail);
        } else if (i == R.id.signInMetod) {
            signInEmail(mEmailField.getText().toString(), mPasswordField.getText().toString());
        }
    }
}
