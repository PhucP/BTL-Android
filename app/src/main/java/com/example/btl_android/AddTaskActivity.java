package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.btl_android.Main.Receiver.NotificationScheduler;
import com.example.btl_android.RoomDatabase.AppDatabase;
import com.example.btl_android.RoomDatabase.Entity.Task;
import com.example.btl_android.RoomDatabase.Entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {
    private EditText title, time, date, description;
    private Button addTask;
    private User currentUser;
    private ImageView imgDate, imgTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().hide();

        init();
        activity();
    }

    private void init() {
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        addTask = findViewById(R.id.btnAddTask);
        imgDate = findViewById(R.id.imgDate);
        imgTime = findViewById(R.id.imgTime);

        Intent intent = getIntent();
        currentUser = (User) intent.getSerializableExtra("currentUser");
    }

    private void activity() {
        addTaskActivity();
        changeTimeActivity();
    }

    private void changeTimeActivity() {
        imgTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hh = c.get(Calendar.HOUR_OF_DAY);
                int mm = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int h, int m) {
                        time.setText(h + ":" + m);
                    }
                }, hh, mm, true);
                timePickerDialog.show();
            }
        });

        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int d = c.get(Calendar.DAY_OF_MONTH);
                int m = c.get(Calendar.MONTH);
                int y = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int mm, int d) {
                        date.setText(d + "/" + (mm+1) + "/" + y);
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });
    }

    private void addTaskActivity() {
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = title.getText().toString().trim();
                String newDes = description.getText().toString().trim();
                String newTime = date.getText().toString().trim() + " " + time.getText().toString().trim();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                try {
                    Date date = sdf.parse(newTime);
                } catch (ParseException e) {
                    Toast.makeText(AddTaskActivity.this, "Time didn't formatted", Toast.LENGTH_SHORT).show();
                    return;
                }

                Task newTask = new Task();
                newTask.setDescription(newDes);
                newTask.setTime(newTime);
                newTask.setTitle(newTitle);
                newTask.setUserId(currentUser.getId());

                AddTaskActivity.CreateTask task = new AddTaskActivity.CreateTask();
                task.execute(newTask);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
                        Task getTask = db.taskDao().getTaskInTime(newTask.getUserId(), newTask.getTime(), newTask.getTitle());
                        if (getTask != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setAlarmForThisTask(getTask);
                                    finish();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }

    private void setAlarmForThisTask(Task tempTask) {
        NotificationScheduler.scheduleNotification(getApplicationContext(), tempTask);
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
        }
    }
}