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
        switch (view.getId()) {
            case R.id.buttonStage1:
                intent.putExtra("stage", 1);
                break;
            case R.id.buttonStage2:
                intent.putExtra("stage", 2);
                break;
            case R.id.buttonStage3:
                intent.putExtra("stage", 3);
                break;
        }

        startActivity(intent);
    }

    public void onButtonStageBackToMain(View view) {
        finish();
    }
}
