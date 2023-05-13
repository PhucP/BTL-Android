package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl_android.RoomDatabase.AppDatabase;
import com.example.btl_android.RoomDatabase.Entity.User;

public class RegisterActivity extends AppCompatActivity {
    EditText userName, passWord, reEnterPassWord;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        activity();
    }

    private void init() {
        userName = findViewById(R.id.regisUserName);
        passWord = findViewById(R.id.regisPassWord);
        reEnterPassWord = findViewById(R.id.reEnterRegisPassWord);
        register = findViewById(R.id.btnRegis);
    }

    private void activity() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = userName.getText().toString().trim();
                String newPassword = passWord.getText().toString().trim();
                String reEnterPassword = reEnterPassWord.getText().toString().trim();

                if(!newPassword.equalsIgnoreCase(reEnterPassword)) {
                    Toast.makeText(RegisterActivity.this, "Re-Enter Password not match", Toast.LENGTH_SHORT).show();
                    return; 
                }

                User newUser = new User(newUsername, newPassword);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
                        db.userDao().insertUser(newUser);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "Register complete", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }
                }).start();
            }
        });
    }
}