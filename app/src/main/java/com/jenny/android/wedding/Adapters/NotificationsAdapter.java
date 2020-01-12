package com.jenny.android.wedding.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jenny.android.wedding.Fragments.PhotoDetailFragment;
import com.jenny.android.wedding.Fragments.ProfileFragment;
import com.jenny.android.wedding.R;
import com.jenny.android.wedding.model.Notification;
import com.jenny.android.wedding.model.Post;
import com.jenny.android.wedding.model.User;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    private Context mContext;
    private List<Notification> mNotification;

    private FirebaseUser firebaseUser;
    private boolean postExists;

    public NotificationsAdapter(Context mContext, List<Notification> mNotification) {
        this.mContext = mContext;
        this.mNotification = mNotification;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username, text;
        public ImageView image_profile, post_image;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            username = itemView.findViewById(R.id.username);
            image_profile = itemView.findViewById(R.id.image_profile);
            text = itemView.findViewById(R.id.comment);
            post_image = itemView.findViewById(R.id.post_image);

        }
    }


    @NonNull
    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.notification_item, parent, false);
        return new NotificationsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationsAdapter.ViewHolder holder, int position) {

        final Notification notification = mNotification.get(position);
        holder.text.setText(notification.getText());

        getUserInfo(holder.image_profile, holder.username, notification.getUserid());

        holder.post_image.setVisibility(View.VISIBLE);

        getPostImage(holder.post_image, notification.getPostid());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts").child(notification.getPostid());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Post post = dataSnapshot.getValue(Post.class);
                        if (post != null){
                            SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit();
                            editor.putString("postid", notification.getPostid());
                            editor.apply();

                            ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new PhotoDetailFragment()).addToBackStack(null).commit();


                        } else {
                            Toast.makeText(mContext, "This post no longer exists", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }

    @Override
    public int getItemCount() {
        return mNotification.size();
    }

    private void getUserInfo(final ImageView imageView, final TextView username, String publishedid){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(publishedid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Glide.with(mContext).load(user.getImageurl()).into(imageView);
                username.setText(user.getUsername());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getPostImage(final ImageView imageView, String postid){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts").child(postid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Post post = dataSnapshot.getValue(Post.class);
                if (post != null){
                    Glide.with(mContext).load(post.getPostimage()).into(imageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}