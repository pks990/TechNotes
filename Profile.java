package com.example1.technotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser currentUser;
    TextView textView1, textView2, textView3;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView mHomeImageView = findViewById(R.id.Homebtn);
        ImageView mSearchImageView = findViewById(R.id.Searchbtn);
        ImageView mProfileImageView = findViewById(R.id.Profilebtn);
        button = findViewById(R.id.Logout);
        textView1 = findViewById(R.id.getname);
        textView2 = findViewById(R.id.getemail);
        textView3 = findViewById(R.id.getnumber);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        if (currentUser == null) {
            // User is not signed in
            Intent intent = new Intent(getApplicationContext(), TechNotes.class);
            startActivity(intent);
            finish();
        } else {
            // User is signed in
//            String displayName = currentUser.getDisplayName();
            String email = currentUser.getEmail();
            String phoneNumber = currentUser.getPhoneNumber();

//            textView1.setText(displayName != null ? displayName : "Name not available");
            textView2.setText(email != null ? email : "Email not available");
            textView3.setText(phoneNumber != null ? phoneNumber : "Phone number not available");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), TechNotes.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mHomeImageView.setOnClickListener(view -> {
            Intent intent = new Intent(Profile.this, HomeActivity.class);
            startActivity(intent);
        });

        mSearchImageView.setOnClickListener(view -> {
            Intent intent = new Intent(Profile.this, Notes.class);
            startActivity(intent);
        });

        mProfileImageView.setOnClickListener(view -> {
            Intent intent = new Intent(Profile.this, Profile.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Profile.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
