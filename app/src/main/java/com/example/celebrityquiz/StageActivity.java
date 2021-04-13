package com.example.celebrityquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);
    }

    public void onButtonStartQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("level", "level1");
        intent.putExtra("seconds", "60");
        startActivity(intent);
    }

    public void onButtonStageBackToMain(View view) {
        finish();
    }
}
