package com.jenny.android.wedding.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jenny.android.wedding.Fragments.PhotoDetailFragment;
import com.jenny.android.wedding.R;
import com.jenny.android.wedding.model.Post;
import com.jenny.android.wedding.ui.EngagementActivity;
import com.jenny.android.wedding.ui.FullImageActivity;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;
    private List<String> mImages;

    public ImageAdapter(Context context, List<String> mImages) {
        this.context = context;
        this.mImages = mImages;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false);

        return new ImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {

        final String image = mImages.get(position);
        Glide.with(context).load(image).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent photointent = new Intent(context, FullImageActivity.class);
                photointent.putExtra("url", image);
                view.getContext().startActivity(photointent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);

        }
    }
}
