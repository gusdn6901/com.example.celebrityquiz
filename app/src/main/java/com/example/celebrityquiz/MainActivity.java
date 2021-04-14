package com.example.celebrityquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    public void onButtonGoStage(View view) {
        Intent intent = new Intent(this, StageActivity.class);
        startActivity(intent);
    }

    public void onButtonSetting(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void onButtonRecord(View view) {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }
}