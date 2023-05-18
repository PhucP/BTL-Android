package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_android.Main.Receiver.AlarmReceiver;
import com.example.btl_android.Main.Receiver.NotificationScheduler;
import com.example.btl_android.RoomDatabase.AppDatabase;
import com.example.btl_android.RoomDatabase.Entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    EditText userName, passWord;
    Button login;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        Button btn = findViewById(R.id.testBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đặt thông báo vào thời điểm cụ thể
                String dateTime = "18/05/2023 11:43"; // Thời điểm muốn đặt thông báo
                int taskId = 123; // ID của nhiệm vụ (task)
                String taskTitle = "Task Title"; // Tiêu đề nhiệm vụ
                String taskTime = "Task Time"; // Thông tin thời gian nhiệm vụ

                NotificationScheduler.scheduleNotification(getApplicationContext(), dateTime, taskId, taskTitle, taskTime);

                // Hủy thông báo dựa trên ID của nhiệm vụ
            }
        });

        init();
        activity();
    }

    private void init() {
        userName = findViewById(R.id.loginUserName);
        passWord = findViewById(R.id.loginPassWord);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.txtRegister);
    }

    private void activity() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginUserName = userName.getText().toString().trim();
                String loginPassWord = passWord.getText().toString().trim();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
                        User currentUser = db.userDao().getUserByUsernameAndPassword(loginUserName, loginPassWord);

                        if(currentUser == null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "Login Pass", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("currentUser", currentUser);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}