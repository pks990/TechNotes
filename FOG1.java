package com.example1.technotes;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example1.technotes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FOG1 extends AppCompatActivity {

    private EditText memail;

    private Button mnextfButton;
    private ProgressBar progressBar;

    FirebaseAuth mAuth;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fog1);

        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        memail = findViewById(R.id.edtemailf);
        mnextfButton = findViewById(R.id.Nextbtnf);
        TextView mCreateAccountTextView = findViewById(R.id.Back);

        mCreateAccountTextView.setOnClickListener(view -> {
            Intent intent = new Intent(FOG1.this, TechNotes.class);
            startActivity(intent);
        });

        mnextfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                validateData();

            }
        });

    }

    private void validateData() {
        progressBar.setVisibility(View.GONE);
        email = memail.getText().toString();
        if(email.isEmpty()){
            memail.setError("Required");
        } else {
            forgotPassword();
        }
    }

    private void forgotPassword() {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(FOG1.this, "Cheack Your Email", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), TechNotes.class);
                            startActivity(intent);
                            finish();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(FOG1.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}