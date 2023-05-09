package com.example.btl.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.btl.R;

public class Login extends AppCompatActivity {
    Button login;
    TextView txtRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        init();
        LoginActivity();
    }

    public void init() {
        login = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);
    }

    public void LoginActivity() {
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}