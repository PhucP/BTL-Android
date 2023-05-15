package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl_android.RoomDatabase.AppDatabase;
import com.example.btl_android.RoomDatabase.Entity.Task;
import com.example.btl_android.RoomDatabase.Entity.User;

public class AddTaskActivity extends AppCompatActivity {
    private EditText title, time, date, description;
    private Button addTask;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        init();
        activity();
    }

    private void init() {
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        addTask = findViewById(R.id.btnAddTask);

        Intent intent = getIntent();
        currentUser = (User) intent.getSerializableExtra("currentUser");
    }

    private void activity() {
        addTaskActivity();
    }

    private void addTaskActivity() {
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = title.getText().toString().trim();
                String newDes = description.getText().toString().trim();
                String newTime = date.getText().toString().trim() + " " + time.getText().toString().trim();

                Task newTask = new Task();
                newTask.setDescription(newDes);
                newTask.setTime(newTime);
                newTask.setTitle(newTitle);
                newTask.setUserId(currentUser.getId());

                AddTaskActivity.CreateTask task = new AddTaskActivity.CreateTask();
                task.execute(newTask);
            }
        });
    }

    private class CreateTask extends AsyncTask<Task, Void, Void> {
        @Override
        protected Void doInBackground(Task... tasks) {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            db.taskDao().insertTask(tasks[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(AddTaskActivity.this, "Created", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}