package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                Toast.makeText(RegisterActivity.this, "Test Register", Toast.LENGTH_SHORT).show();
            }
        });
    }
}