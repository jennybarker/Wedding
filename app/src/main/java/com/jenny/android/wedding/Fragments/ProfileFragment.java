package com.jenny.android.wedding.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jenny.android.wedding.Adapters.PhotoAdapter;
import com.jenny.android.wedding.Adapters.PostAdapter;
import com.jenny.android.wedding.EditProfileActivity;
import com.jenny.android.wedding.OptionsActivity;
import com.jenny.android.wedding.R;
import com.jenny.android.wedding.model.Post;
import com.jenny.android.wedding.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProfileFragment extends Fragment {

    ImageView image_profile, options;
    TextView posts, followers, following, fullname, bio, username;
    ImageButton edit_profile;

    FirebaseUser firebaseUser;
    String profileid;

    RecyclerView recyclerView;
    PhotoAdapter photoAdapter;
    List<Post> postList;

    private List<String> mySaves;

    RecyclerView recyclerView_saves;
    PhotoAdapter photoAdapter_saves;
    List<Post> postList_saves;


    ImageButton my_photos, saved_photos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences prefs = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid = prefs.getString("profileid", "none");



        image_profile = view.findViewById(R.id.image_profile);
        options = view.findViewById(R.id.options);
        fullname = view.findViewById(R.id.fullname);
        username = view.findViewById(R.id.username);
        bio = view.findViewById(R.id.bio);
        edit_profile = view.findViewById(R.id.edit_profile);
        my_photos = view.findViewById(R.id.my_photos);
        saved_photos = view.findViewById(R.id.saved_photos);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(linearLayoutManager);
        postList = new ArrayList<>();
        photoAdapter = new PhotoAdapter(getContext(), postList);
        recyclerView.setAdapter(photoAdapter);

        recyclerView_saves = view.findViewById(R.id.recycler_view_save);
        recyclerView_saves.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager_saves = new GridLayoutManager(getContext(), 3);
        recyclerView_saves.setLayoutManager(linearLayoutManager_saves);
        postList_saves = new ArrayList<>();
        photoAdapter_saves = new PhotoAdapter(getContext(), postList_saves);
        recyclerView_saves.setAdapter(photoAdapter_saves);

        recyclerView.setVisibility(View.VISIBLE);
        recyclerView_saves.setVisibility(View.GONE);

        getUserInfo();
        getMyPhotos();
        getSavedPhotos();

        if (profileid.equals(firebaseUser.getUid())){

            edit_profile.setVisibility(View.VISIBLE);

        } else {
            saved_photos.setVisibility(View.GONE);
            edit_profile.setVisibility(View.GONE);
        }

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //go to edit profile
                startActivity(new Intent(getContext(), EditProfileActivity.class));
            }
        });

        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OptionsActivity.class);
                startActivity(intent);
            }
        });

        my_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView_saves.setVisibility(View.GONE);
            }
        });

        saved_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.GONE);
                recyclerView_saves.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    private void getUserInfo(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(profileid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(getContext() == null){
                    return;
                }

                User user = dataSnapshot.getValue(User.class);

                Glide.with(getContext()).load(user.getImageurl()).into(image_profile);
                username.setText(user.getUsername());
                fullname.setText(user.getFullname());
                bio.setText(user.getBio());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getMyPhotos(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Post post = snapshot.getValue(Post.class);
                    if (post.getPublisher().equals(profileid)){
                        postList.add(post);
                    }
                }
                Collections.reverse(postList);
                photoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getSavedPhotos(){

        mySaves = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Saves").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    mySaves.add(snapshot.getKey());
                }

                readSaves();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void readSaves(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList_saves.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Post post = snapshot.getValue(Post.class);

                    for(String id : mySaves){
                        if(post.getPostid().equals(id)){
                            postList_saves.add(post);
                        }
                    }
                }

                photoAdapter_saves.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
