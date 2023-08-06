package com.example1.technotes;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example1.technotes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth  auth;
    FirebaseUser currentUser;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView mHomeImageView = findViewById(R.id.Homebtn);
        ImageView mSearchImageView = findViewById(R.id.Searchbtn);
        ImageView mProfileImageView = findViewById(R.id.Profilebtn);
//        textView = findViewById()
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

//        if(currentUser == null){
//            Intent intent = new Intent(getApplicationContext(), TechNotes.class);
//            startActivity(intent);
//            finish();
//        }
//        else {
//            textView.setText(currentUser.getDisplayName());
//        }


        ImageView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mHomeImageView.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        mSearchImageView.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, Notes.class);
            startActivity(intent);
        });

        mProfileImageView.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, Profile.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override

    public void onBackPressed() {
        Intent intent = new Intent(this, TechNotes.class);
        startActivity(intent);
        finish();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getActionBar().setDisplayShowTitleEnabled(false);
//
//        getMenuInflater().inflate(R.menu.menu_item,menu);
//        return true;
//    }
}
