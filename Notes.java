package com.example1.technotes;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

public class Notes extends AppCompatActivity {

    private Button mcourseButton;
    private Button msubjectButton;
    private Button mfindButton;
    private SearchView mSearchView;

    String C, Java;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        ImageView mHomeImageView = findViewById(R.id.Homebtn);
        ImageView mSearchImageView = findViewById(R.id.Searchbtn);
        ImageView mProfileImageView = findViewById(R.id.Profilebtn);
        mfindButton = findViewById(R.id.Find);
        mSearchView = findViewById(R.id.searchView);

        mfindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = mSearchView.getQuery().toString();
                C = "C: " + searchText;

                if (TextUtils.isEmpty(searchText)) {
                    Toast.makeText(Notes.this, "Enter Course Name", Toast.LENGTH_SHORT).show();
                    return;
                } else if (searchText.equals("BCA")) { // Use .equals() to compare strings
                    Intent intent = new Intent(Notes.this, C.class);
                    startActivity(intent);
                    finish();
                }else if (searchText.equals("bca")) { // Use .equals() to compare strings
                    Intent intent = new Intent(Notes.this, C.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Notes.this, "Currently, we only provide BCA Notes", Toast.LENGTH_SHORT).show();
                }
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
            Intent intent = new Intent(Notes.this, HomeActivity.class);
            startActivity(intent);
        });

        mSearchImageView.setOnClickListener(view -> {
            Intent intent = new Intent(Notes.this, Notes.class);
            startActivity(intent);
        });

        mProfileImageView.setOnClickListener(view -> {
            Intent intent = new Intent(Notes.this, Profile.class);
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
        Intent intent = new Intent(Notes.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}