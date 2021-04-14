package com.example.celebrityquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class StageActivity extends AppCompatActivity {
    private TextView textViewCategory1;
    private TextView textViewStage1;
    private TextView textViewScore1;
    private TextView textViewTime1;
    private TextView textViewCategory2;
    private TextView textViewStage2;
    private TextView textViewScore2;
    private TextView textViewTime2;
    private TextView textViewCategory3;
    private TextView textViewStage3;
    private TextView textViewScore3;
    private TextView textViewTime3;
    private TextView textViewCategory4;
    private TextView textViewStage4;
    private TextView textViewScore4;
    private TextView textViewTime4;
    private TextView textViewCategory5;
    private TextView textViewStage5;
    private TextView textViewScore5;
    private TextView textViewTime5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);
        textViewCategory1 = findViewById(R.id.textViewCategory1);
        textViewStage1 = findViewById(R.id.textViewStage1);
        textViewScore1 = findViewById(R.id.textViewScore1);
        textViewTime1 = findViewById(R.id.textViewTime1);
        textViewCategory2 = findViewById(R.id.textViewCategory2);
        textViewStage2 = findViewById(R.id.textViewStage2);
        textViewScore2 = findViewById(R.id.textViewScore2);
        textViewTime2 = findViewById(R.id.textViewTime2);
        textViewCategory3 = findViewById(R.id.textViewCategory3);
        textViewStage3 = findViewById(R.id.textViewStage3);
        textViewScore3 = findViewById(R.id.textViewScore3);
        textViewTime3 = findViewById(R.id.textViewTime3);
        textViewCategory4 = findViewById(R.id.textViewCategory4);
        textViewStage4 = findViewById(R.id.textViewStage4);
        textViewScore4 = findViewById(R.id.textViewScore4);
        textViewTime4 = findViewById(R.id.textViewTime4);
        textViewCategory5 = findViewById(R.id.textViewCategory5);
        textViewStage5 = findViewById(R.id.textViewStage5);
        textViewScore5 = findViewById(R.id.textViewScore5);
        textViewTime5 = findViewById(R.id.textViewTime5);

        ArrayList<TextView> views = new ArrayList<>();
        views.add(textViewCategory1);
        views.add(textViewStage1);
        views.add(textViewScore1);
        views.add(textViewTime1);
        views.add(textViewCategory2);
        views.add(textViewStage2);
        views.add(textViewScore2);
        views.add(textViewTime2);
        views.add(textViewCategory3);
        views.add(textViewStage3);
        views.add(textViewScore3);
        views.add(textViewTime3);
        views.add(textViewCategory4);
        views.add(textViewStage4);
        views.add(textViewScore4);
        views.add(textViewTime4);
        views.add(textViewCategory5);
        views.add(textViewStage5);
        views.add(textViewScore5);
        views.add(textViewTime5);

        SharedPreferences sf = getSharedPreferences("setting", MODE_PRIVATE);
        if (sf.getString("mode", "객관식").equals("객관식"))
            sf = getSharedPreferences("Log_mode1", MODE_PRIVATE);
        else sf = getSharedPreferences("Log_mode2", MODE_PRIVATE);

        int logLocation = sf.getInt("size", 0);
        int endIter = logLocation-5;
        int j = 0;
        for (; logLocation > endIter; logLocation--) {
            views.get(j++).setText(sf.getString("category"+logLocation, ""));
            views.get(j++).setText(sf.getString("stage"+logLocation, ""));
            views.get(j++).setText(sf.getString("score"+logLocation, ""));
            views.get(j++).setText(sf.getString("seconds"+logLocation, ""));
        }
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
