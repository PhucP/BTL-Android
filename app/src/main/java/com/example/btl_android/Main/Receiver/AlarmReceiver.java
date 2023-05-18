package com.example.btl_android.Main.Receiver;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.btl_android.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the task information from the Intent.
        int taskId = intent.getIntExtra("taskId", -1);
        String taskTitle = intent.getStringExtra("taskTitle");
        String taskTime = intent.getStringExtra("taskTime");

        // Check if the app has permission to post notifications.
        String permission = Manifest.permission.ACCESS_NOTIFICATION_POLICY;
        int result = ContextCompat.checkSelfPermission(context, permission);

        // If the app does not have permission, request permission from the user.
        if (result != PackageManager.PERMISSION_GRANTED && context instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, 123);
        } else {
            // The app has permission to post notifications.
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelId")
                    .setContentTitle(taskTitle)
                    .setContentText(taskTime)
                    .setSmallIcon(R.drawable.notifi)
                    .setAutoCancel(true);

            // Use NotificationManagerCompat to post the notification.
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(taskId, builder.build());
        }
    }
}
