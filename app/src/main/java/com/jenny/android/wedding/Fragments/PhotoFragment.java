package com.jenny.android.wedding.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jenny.android.wedding.Adapters.PostAdapter;
import com.jenny.android.wedding.PostActivity;
import com.jenny.android.wedding.R;
import com.jenny.android.wedding.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PhotoFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList;

    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        ImageView add_image_button = view.findViewById(R.id.add_image);
        ImageView notification_button = view.findViewById(R.id.notifications);
        progressBar = view.findViewById(R.id.progress_circular);

        add_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PostActivity.class);
                startActivity(i);
            }
        });

        notification_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NotificationFragment()).addToBackStack(null).commit();
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        postList = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), postList);
        recyclerView.setAdapter(postAdapter);

        readPosts();

        // Inflate the layout for this fragment
        return view;
    }

    private void readPosts() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Post post = snapshot.getValue(Post.class);
                    postList.add(post);
                    progressBar.setVisibility(View.GONE);

                }

                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
