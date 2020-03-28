package com.jenny.android.wedding.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jenny.android.wedding.Adapters.ImageAdapter;
import com.jenny.android.wedding.Adapters.PhotoAdapter;
import com.jenny.android.wedding.R;
import com.jenny.android.wedding.model.Post;
import com.jenny.android.wedding.model.User;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EngagementActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageAdapter imageAdapter;
    List<String> gallaryList;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engagement);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        progressBar = findViewById(R.id.progress_circular);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getBaseContext(), 2);
        recyclerView.setLayoutManager(linearLayoutManager);
        gallaryList = new ArrayList<>();
        imageAdapter = new ImageAdapter(getBaseContext(), gallaryList);
        recyclerView.setAdapter(imageAdapter);

        getEngagementPhotos();

    }

    private void getEngagementPhotos(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Engagement");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                gallaryList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String url = snapshot.getValue().toString();

                    gallaryList.add(url);
                    progressBar.setVisibility(View.GONE);
                }

                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
