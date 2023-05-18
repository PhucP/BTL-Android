package com.example.btl_android.Main.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationManagerCompat;

import com.example.btl_android.RoomDatabase.AppDatabase;
import com.example.btl_android.RoomDatabase.Dao.TaskDao;
import com.example.btl_android.RoomDatabase.Entity.Task;

public class CompleteTaskReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int taskId = intent.getIntExtra("taskId", -1);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getDatabase(context);
                TaskDao taskDao = db.taskDao();
                Task task = taskDao.findById(taskId);

                task.setStats("completed");
                taskDao.updateTask(task);
            }
        });

        thread.start();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.cancel(taskId);
        
        Toast.makeText(context, "Task completed", Toast.LENGTH_SHORT).show();
    }
}
