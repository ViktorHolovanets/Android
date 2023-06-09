package com.study.books;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = null;
        int id = view.getId();
        if (id == R.id.books) {
            intent = new Intent(this, Books.class);
        } else if (id == R.id.dogs) {
            intent = new Intent(this, Dogs.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}