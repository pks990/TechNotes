package com.example1.technotes;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class C extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        ImageView mHomeImageView = findViewById(R.id.Homebtn);
        ImageView mSearchImageView = findViewById(R.id.Searchbtn);
        ImageView mProfileImageView = findViewById(R.id.Profilebtn);
        ImageView backButton = findViewById(R.id.back);
        Button msub1 = findViewById(R.id.sub1);
        Button msub2 = findViewById(R.id.sub2);
        Button msub3 = findViewById(R.id.sub3);
        Button msub4 = findViewById(R.id.sub4);

        msub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PDfActivity.class);
                intent.putExtra("pdf_url", "https://vardhaman.org/wp-content/uploads/2021/03/CP.pdf");
                startActivity(intent);
                finish();
            }
        });

        msub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PDfActivity.class);
                intent.putExtra("pdf_url", "https://www.cet.edu.in/noticefiles/285_OOPS%20lecture%20notes%20Complete.pdf");
                startActivity(intent);
                finish();
            }
        });

        msub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PDfActivity.class);
                intent.putExtra("pdf_url", "https://mrcet.com/downloads/digital_notes/IT/JAVA%20PROGRAMMING.pdf");
                startActivity(intent);
                finish();
            }
        });

        msub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PDfActivity.class);
                intent.putExtra("pdf_url", "http://www.iimchyderabad.com/Material/html.pdf");
                startActivity(intent);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });



        mHomeImageView.setOnClickListener(view -> {
            Intent intent = new Intent(C.this, HomeActivity.class);
            startActivity(intent);
        });

        mSearchImageView.setOnClickListener(view -> {
            Intent intent = new Intent(C.this, Notes.class);
            startActivity(intent);
        });

        mProfileImageView.setOnClickListener(view -> {
            Intent intent = new Intent(C.this, Profile.class);
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
        Intent intent = new Intent(C.this, Notes.class);
        startActivity(intent);
        finish();
    }
}