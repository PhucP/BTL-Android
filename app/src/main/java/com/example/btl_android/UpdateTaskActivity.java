package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_android.RoomDatabase.AppDatabase;
import com.example.btl_android.RoomDatabase.Entity.Task;
import com.example.btl_android.RoomDatabase.Entity.User;

public class UpdateTaskActivity extends AppCompatActivity {
    private EditText title, description, date, time;
    private Spinner spinner;
    private Button updateTask;
    private TextView deleteTask;
    private Intent intent;
    private Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        getSupportActionBar().hide();

        intent = getIntent();
        currentTask = (Task) intent.getSerializableExtra("currentTask");

        init();
        setTask();
        activity();
    }

    private void setTask() {
        title.setText(currentTask.getTitle());
        description.setText(currentTask.getDescription());
        String dateTime = currentTask.getTime();
        String tempDate = dateTime.split(" ")[0];
        String tempTime = dateTime.split(" ")[1];
        date.setText(tempDate);
        time.setText(tempTime);

        if(currentTask.getStats().equalsIgnoreCase("next_task")) {
            spinner.setSelection(0);
        } else if(currentTask.getStats().equalsIgnoreCase("completed")) {
            spinner.setSelection(1);
        } else {
            spinner.setSelection(2);
        }
    }

    private void init() {
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        spinner = findViewById(R.id.spinner);
        updateTask = findViewById(R.id.btnUpdateTask);
        deleteTask = findViewById(R.id.deleteTask);
    }

    private void activity() {
        updateTaskActivity();
        deleteTaskActivity();
    }

    private void updateTaskActivity() {
        updateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateTitle = title.getText().toString().trim();
                String updateDes = description.getText().toString().trim();
                String updateTime = date.getText().toString().trim() + " " + time.getText().toString().trim();
                String updateStats = spinner.getSelectedItem().toString();

                Task updatedTask = new Task();
                updatedTask.setUserId(currentTask.getUserId());
                updatedTask.setId(currentTask.getId());
                updatedTask.setTitle(updateTitle);
                updatedTask.setTime(updateTime);
                updatedTask.setStats(updateStats);
                updatedTask.setDescription(updateDes);

                UpdateTaskActivity.UpdateTask task = new UpdateTaskActivity.UpdateTask();
                task.execute(updatedTask);
            }
        });
    }

    private class UpdateTask extends AsyncTask<Task, Void, Void> {
        @Override
        protected Void doInBackground(Task... tasks) {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            db.taskDao().updateTask(tasks[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(UpdateTaskActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void deleteTaskActivity() {
        deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateTaskActivity.DeleteTask task = new UpdateTaskActivity.DeleteTask();
                task.execute(currentTask);
            }
        });
    }
    private class DeleteTask extends AsyncTask<Task, Void, Void> {
        @Override
        protected Void doInBackground(Task... tasks) {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            db.taskDao().deleteTask(tasks[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(UpdateTaskActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}