package com.example.billingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {
    EditText loginUsername;
    EditText loginPhone;
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername=(EditText) findViewById(R.id.loginUsername);
        loginPhone=(EditText) findViewById(R.id.registerPhone);
        loginBtn= (Button) findViewById(R.id.loginBtn);


    }
}