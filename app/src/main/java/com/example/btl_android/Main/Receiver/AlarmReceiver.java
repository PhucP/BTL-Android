package com.example.btl_android.Main.Receiver;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.btl_android.R;
import com.example.btl_android.RoomDatabase.Entity.Task;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int taskId = intent.getIntExtra("taskId", -1);
        String taskTitle = intent.getStringExtra("taskTitle");
        String taskDes = intent.getStringExtra("taskDes");

        String permission = Manifest.permission.ACCESS_NOTIFICATION_POLICY;
        int result = ContextCompat.checkSelfPermission(context, permission);

        if (result != PackageManager.PERMISSION_GRANTED && context instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, 123);
        } else {
            Intent completeIntent = new Intent(context, CompleteTaskReceiver.class);
            completeIntent.putExtra("taskId", taskId);
            PendingIntent completePendingIntent = PendingIntent.getBroadcast(context, taskId, completeIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Intent unCompleteIntent = new Intent(context, UnCompleteTaskReceiver.class);
            unCompleteIntent.putExtra("taskId", taskId);
            PendingIntent unCompletePendingIntent = PendingIntent.getBroadcast(context, taskId, unCompleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelId")
                    .setContentTitle(taskTitle)
                    .setContentText(taskDes)
                    .setSmallIcon(R.drawable.notifi)
                    .setAutoCancel(true)
                    .addAction(R.drawable.baseline_add_task_24, "Complete", completePendingIntent)
                    .addAction(R.drawable.baseline_task_24, "UnComplete", unCompletePendingIntent);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(taskId, builder.build());
        }
    }
}
