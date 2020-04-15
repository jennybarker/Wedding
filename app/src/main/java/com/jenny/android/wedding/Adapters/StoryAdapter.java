package com.jenny.android.wedding.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jenny.android.wedding.R;
import com.jenny.android.wedding.model.Post;
import com.jenny.android.wedding.model.Story;

import java.io.OutputStream;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {


    public Context mContext;
    public List<Story> mStory;


    public StoryAdapter(Context mContext, List<Story> mStory) {
        this.mContext = mContext;
        this.mStory = mStory;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView event_image;
        public TextView event_title, event_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            event_image = itemView.findViewById(R.id.event_image);
            event_description = itemView.findViewById(R.id.event_description);
            event_title = itemView.findViewById(R.id.event_title);

        }

    }

    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.our_story_item, parent, false);

        return new StoryAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder holder, int position) {

        final Story event = mStory.get(position);

        Glide.with(mContext).load(event.getStoryimage())
                .apply(new RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(holder.event_image);


        holder.event_title.setText(event.getTitle());
        holder.event_description.setText(event.getDescription());


    }

    @Override
    public int getItemCount() {
        return mStory.size();
    }
}
