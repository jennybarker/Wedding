package com.jenny.android.wedding.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jenny.android.wedding.Adapters.ImageAdapter;
import com.jenny.android.wedding.Adapters.StoryAdapter;
import com.jenny.android.wedding.R;
import com.jenny.android.wedding.model.Story;

import java.util.ArrayList;
import java.util.List;

public class OurStoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StoryAdapter storyAdapter;
    List<Story> storyList;

    private ProgressBar progressBar;
    private LinearLayout noInternetMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_story);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        progressBar = findViewById(R.id.progress_circular);
        noInternetMessage = findViewById(R.id.no_internet_message);
        noInternetMessage.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        storyList = new ArrayList<>();
        storyAdapter = new StoryAdapter(getBaseContext(), storyList);
        recyclerView.setAdapter(storyAdapter);

        if(isNetworkAvailable(getBaseContext())){

            getStoryEvents();

        } else {
            progressBar.setVisibility(View.GONE);
            noInternetMessage.setVisibility(View.VISIBLE);
        }


    }

    private void getStoryEvents(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Story");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                storyList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Story event = snapshot.getValue(Story.class);

                    storyList.add(event);
                    progressBar.setVisibility(View.GONE);
                }

                storyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
