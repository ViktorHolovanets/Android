package com.study.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.study.books.adapter.ImageAdapter;
import com.study.books.model.ImageSourseModel;
import com.study.books.services.httpservice.VolleyService;

import org.json.JSONException;

import java.util.ArrayList;

public class Dogs extends AppCompatActivity {

    ArrayList<ImageSourseModel> states = new ArrayList<ImageSourseModel>();
    ImageAdapter stateAdapter;
    ListView dogsList;
    VolleyService volleyService;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        volleyService =new VolleyService(this);
        setInitialData();
        dogsList = findViewById(R.id.list_view);
        stateAdapter = new ImageAdapter(this, R.layout.list_image, states);
        dogsList.setAdapter(stateAdapter);
        dogsList.setOnItemClickListener(this::onItemClickSelectItem);
    }
    private void setInitialData(){
        for (int i=0; i<10;i++){
            loadDogImage();
        }
    }

    private void loadDogImage() {
        String url = "https://dog.ceo/api/breeds/image/random";
        volleyService.loadGet(url, imageUrl -> {
            try {
                states.add(new ImageSourseModel(imageUrl.getString("message")));
                stateAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void onClickUpdateImages(View view) {
        states.clear();
        setInitialData();
    }
    public void onItemClickSelectItem(AdapterView<?> parent, View v, int position, long id)
    {
        String selectedItem = states.get(position).getImgResource();
        ConstraintLayout constraintLayout=(ConstraintLayout)findViewById(R.id.layout_content);
        ImageView imageView = new ImageView(constraintLayout.getContext());
        Glide.with(this).load(selectedItem).into(imageView);

        imageView.setScaleType(ImageView.ScaleType.CENTER);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT , ConstraintLayout.LayoutParams.MATCH_PARENT);
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        imageView.setBackgroundResource(R.color.transparent_gray);
        imageView.setLayoutParams(layoutParams);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.removeView(imageView);
            }
        });
        constraintLayout.addView(imageView);
        setContentView(constraintLayout);
    }

}