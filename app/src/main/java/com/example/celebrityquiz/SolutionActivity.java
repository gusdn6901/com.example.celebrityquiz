package com.example.celebrityquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SolutionActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        // Define Navigation
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Results");
        }

        // Interface instance to get values from QuizActivity
        int scoreValue = getIntent().getIntExtra("score", 0);
        List<Quiz> quizList = (List<Quiz>) getIntent().getSerializableExtra("quizList");

        // Set view and display scoreValue
        TextView scoreView = findViewById(R.id.scoreTextView);
        scoreView.setText(String.valueOf(scoreValue));

        // Set score out-of view
        TextView scoreTotalView = findViewById(R.id.scoreTotalTextView);
        scoreTotalView.setText(String.valueOf(3));

        // RecycleView definitions
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SolutionAdapter solutionAdapter = new SolutionAdapter(quizList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(solutionAdapter);
    }
}
