package com.example1.technotes;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example1.technotes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TechNotes extends AppCompatActivity {

    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    private String mUsername;
    private String mPassword;
    Button mLoginButton;
    ImageView mgoogle;

    FirebaseAuth mAuth;
    FirebaseUser currentUsers;

    String name, email, mobile_no, password;
    ProgressBar progressBar;

//    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tech_notes);

        mAuth = FirebaseAuth.getInstance();
        currentUsers = mAuth.getCurrentUser();
        progressBar = findViewById(R.id.progressBar);
        mEmailEditText = findViewById(R.id.edtemail);
        mPasswordEditText = findViewById(R.id.edtpassword);
        mLoginButton = findViewById(R.id.loginbtn);
        TextView mForgotPasswordTextView = findViewById(R.id.forgot_password);
        TextView mCreateAccountTextView = findViewById(R.id.create_account);

        mForgotPasswordTextView.setOnClickListener(view -> {
            // TODO: Implement forgot password functionality
            Intent intent = new Intent(TechNotes.this, FOG1.class);
            startActivity(intent);
        });

        mCreateAccountTextView.setOnClickListener(view -> {
            Intent intent = new Intent(TechNotes.this, SignUp.class);
            startActivity(intent);
        });


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                email = String.valueOf(mEmailEditText.getText());
                password = String.valueOf(mPasswordEditText.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(TechNotes.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(TechNotes.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                    Toast.makeText(TechNotes.this, "Login Succussfull", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(TechNotes.this, "Login failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }


}
