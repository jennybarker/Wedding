package com.jenny.android.wedding.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jenny.android.wedding.ui.EngagementActivity;
import com.jenny.android.wedding.ui.HenPartyActivity;
import com.jenny.android.wedding.ui.ItineraryActivity;
import com.jenny.android.wedding.ui.OrderOfServiceActivity;
import com.jenny.android.wedding.R;
import com.jenny.android.wedding.ui.OurStoryActivity;
import com.jenny.android.wedding.ui.StagPartyActivity;
import com.jenny.android.wedding.ui.WeddingBreakfastActivity;
import com.jenny.android.wedding.ui.WeddingPartyActivity;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        final Button OrderOfService = view.findViewById(R.id.button_order_of_service);
        Button WeddingBreakfast = view.findViewById(R.id.button_breakfast);
        Button WeddingParty = view.findViewById(R.id.button_wedding_party);
        final Button Itinerary = view.findViewById(R.id.button_itinerary);
        Button Engagement = view.findViewById(R.id.button_engagement);
        Button OurStory = view.findViewById(R.id.button_story);
        Button HenParty = view.findViewById(R.id.button_hen);
        Button StagParty = view.findViewById(R.id.button_stag);

        OrderOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), OrderOfServiceActivity.class));

            }
        });

        WeddingBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), WeddingBreakfastActivity.class));
            }
        });


        WeddingParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), WeddingPartyActivity.class));
            }
        });

        Itinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ItineraryActivity.class));
            }
        });

        Engagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), EngagementActivity.class));
            }
        });

        OurStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), OurStoryActivity.class));
            }
        });

        HenParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), HenPartyActivity.class));
            }
        });

        StagParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), StagPartyActivity.class));
            }
        });
        return view;

    }




}
