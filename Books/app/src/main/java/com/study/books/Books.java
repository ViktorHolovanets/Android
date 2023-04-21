package com.study.books;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.study.books.adapter.BooksAdapter;
import com.study.books.adapter.ImageAdapter;
import com.study.books.model.BooksModel;
import com.study.books.model.ImageSourceModel;
import com.study.books.services.httpservice.VolleyService;

import org.json.JSONException;

import java.util.ArrayList;

public class Books extends AppCompatActivity {
    VolleyService volleyService;
    ArrayList<BooksModel> states = new ArrayList<BooksModel>();
    BooksAdapter stateAdapter;
    ListView booksList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        volleyService = new VolleyService(this);
        Spinner spinner = findViewById(R.id.spinner_genres);
        spinner.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.genres)));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = (String) parent.getItemAtPosition(position);
                Toast t = Toast.makeText(Books.this, item, Toast.LENGTH_SHORT);
                t.show();
                LoadBooks(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        booksList = findViewById(R.id.list_books);
        stateAdapter = new BooksAdapter(this, R.layout.list_books, states);
        booksList.setAdapter(stateAdapter);
    }

    private void LoadBooks(String genre) {
        String url = "https://openlibrary.org/subjects/"+genre+".json";
        volleyService.loadGet(url, imageUrl -> {
            try {
                states.clear();
                states.addAll(BooksModel.fromJsonList(imageUrl));
                stateAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void onClickBack(View view) {
        finish();
    }
}