package com.jenny.android.wedding.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jenny.android.wedding.R;

public class WeddingPartyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_party);

        getImageBex();
        getImageBecky();
        getImageJo();
        getImageEllie();
        getImageAndy();
        getImageRoss();
        getImageStephen();
        getImageRichard();

    }

    private void getImageBex(){

        final ImageView ProfileBex = findViewById(R.id.image_profile_bex);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("WeddingParty").child("bex");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String image = dataSnapshot.getValue().toString();

                Glide.with(getBaseContext()).load(image).into(ProfileBex);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getImageBecky(){

        final ImageView ProfileBecky = findViewById(R.id.image_profile_becky);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("WeddingParty").child("becky");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String image = dataSnapshot.getValue().toString();

                Glide.with(getBaseContext()).load(image).into(ProfileBecky);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getImageJo(){

        final ImageView ProfileJo = findViewById(R.id.image_profile_jo);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("WeddingParty").child("jo");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String image = dataSnapshot.getValue().toString();

                Glide.with(getBaseContext()).load(image).into(ProfileJo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getImageEllie(){

        final ImageView ProfileEllie = findViewById(R.id.image_profile_ellie);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("WeddingParty").child("ellie");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String image = dataSnapshot.getValue().toString();

                Glide.with(getBaseContext()).load(image).into(ProfileEllie);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getImageAndy(){

        final ImageView ProfileAndy = findViewById(R.id.image_profile_andy);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("WeddingParty").child("andy");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String image = dataSnapshot.getValue().toString();

                Glide.with(getBaseContext()).load(image).into(ProfileAndy);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getImageRoss(){

        final ImageView ProfileRoss = findViewById(R.id.image_profile_ross);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("WeddingParty").child("ross");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String image = dataSnapshot.getValue().toString();

                Glide.with(getBaseContext()).load(image).into(ProfileRoss);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getImageStephen(){

        final ImageView ProfileStephen = findViewById(R.id.image_profile_steven);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("WeddingParty").child("stephen");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String image = dataSnapshot.getValue().toString();

                Glide.with(getBaseContext()).load(image).into(ProfileStephen);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getImageRichard(){

        final ImageView ProfileRichard = findViewById(R.id.image_profile_richard);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("WeddingParty").child("richard");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String image = dataSnapshot.getValue().toString();

                Glide.with(getBaseContext()).load(image).into(ProfileRichard);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
