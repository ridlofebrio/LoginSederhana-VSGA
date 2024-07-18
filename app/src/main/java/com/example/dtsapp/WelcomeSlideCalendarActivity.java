package com.example.dtsapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeSlideCalendarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_slide);
    }

    public void clickGetStarted(View view) {
        Intent i = new Intent(WelcomeSlideCalendarActivity.this, WelcomeSlideSuperheroActivity.class);
        startActivity(i);
    }

    public void clickLogin(View view) {
        Intent i = new Intent(WelcomeSlideCalendarActivity.this, WelcomeBackActivity.class);
        startActivity(i);
    }
}
