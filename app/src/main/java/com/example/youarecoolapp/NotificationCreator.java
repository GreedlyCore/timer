package com.example.youarecoolapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationCreator {

    public void create(String textContent, String textTitle, String CHANNEL_ID){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Constants.getContext(), CHANNEL_ID)
                //.setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        ;

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Constants.getContext());

// notificationId is a unique int for each notification that you must define
        int notificationId = 110;
        notificationManager.notify(notificationId, builder.build());
    }

    private void createNotificationChannel(String CHANNEL_ID) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MAIN_NOTICE";
            String description = "MAIN_DESCRIPTION";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = Constants.getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
