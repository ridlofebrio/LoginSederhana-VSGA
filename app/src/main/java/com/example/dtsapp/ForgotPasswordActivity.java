package com.example.dtsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void postSendRequest(View view) {
        Intent i = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
        startActivity(i);
    }
}
