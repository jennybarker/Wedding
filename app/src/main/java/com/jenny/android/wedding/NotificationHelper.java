package com.jenny.android.wedding;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.jenny.android.wedding.Fragments.NotificationFragment;
import com.jenny.android.wedding.model.Notification;
import com.jenny.android.wedding.ui.MainActivity;

import static com.jenny.android.wedding.Fragments.NotificationFragment.CHANNEL_ID;

public class NotificationHelper {

    public static void displayNotification(Context context, String title, String body){

        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                100,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.wedding_icon)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);


        NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(context);
        mNotificationManager.notify(1, mBuilder.build());
    }

}
