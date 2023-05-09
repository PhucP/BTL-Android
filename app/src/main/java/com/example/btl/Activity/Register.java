package com.example.btl.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.btl.R;

public class Register extends AppCompatActivity {
    EditText regisUserName;
    EditText regisPassWord;
    EditText reEnterRegisPassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    public void init() {
        reEnterRegisPassWord = findViewById(R.id.reEnterRegisPassWord);
        regisUserName = findViewById(R.id.regisUserName);
        regisPassWord = findViewById(R.id.regisPassWord);
    }
}