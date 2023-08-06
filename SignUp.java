package com.example1.technotes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mEmailEditText;

    private EditText mmobileEditText;
    private EditText mPasswordEditText;
    private Button mSignUpButton;
    FirebaseAuth mAuth;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize the views
        mAuth = FirebaseAuth.getInstance();
        mNameEditText = findViewById(R.id.edtname);
        mEmailEditText = findViewById(R.id.edtemail);
        mmobileEditText = findViewById(R.id.edtmob);
        mPasswordEditText = findViewById(R.id.edtpassword);
        mSignUpButton = findViewById(R.id.create_ac);
        progressBar = findViewById(R.id.progressBar);
        TextView mLoginAccountTextView = findViewById(R.id.create_account);

        mLoginAccountTextView.setOnClickListener(view -> {
            Intent intent = new Intent(SignUp.this, TechNotes.class);
            startActivity(intent);
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                name = String.valueOf(mNameEditText.getText());
                email = String.valueOf(mEmailEditText.getText());
                mobile_no = String.valueOf(mmobileEditText.getText());
                password = String.valueOf(mPasswordEditText.getText());

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(SignUp.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUp.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(mobile_no)){
                    Toast.makeText(SignUp.this, "Enter Mobile NO", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUp.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(SignUp.this, "Account Created.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), TechNotes.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(SignUp.this, "Sign up failed. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}
