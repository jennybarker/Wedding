package com.jenny.android.wedding.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jenny.android.wedding.Adapters.ImageAdapter;
import com.jenny.android.wedding.R;

public class FullImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);


        //Get intent data
        Intent intent = getIntent();

        //Get the image position from the intent
        String url = intent.getExtras().getString("url");

        ImageView imageView = (ImageView) findViewById(R.id.full_image);
        Glide.with(getBaseContext()).load(url).into(imageView);

    }
}
