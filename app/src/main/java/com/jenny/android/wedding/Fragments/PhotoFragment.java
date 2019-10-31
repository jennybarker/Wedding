package com.jenny.android.wedding.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jenny.android.wedding.PostActivity;
import com.jenny.android.wedding.R;

public class PhotoFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        ImageView add_image_button = view.findViewById(R.id.add_image);

        add_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PostActivity.class);
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
