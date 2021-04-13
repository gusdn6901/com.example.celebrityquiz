package com.example.celebrityquiz;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        SharedPreferences sf = getSharedPreferences("starboolean", MODE_PRIVATE);
        List<Quiz> list = new ArrayList<>();
        Resources res = getResources();
        int[] numbers = res.getIntArray(R.array.numbers);
        String[] questions = res.getStringArray(R.array.questions);
        String[] imageUrls = res.getStringArray(R.array.imageUrls);
        String[] select1 = res.getStringArray(R.array.select1);
        String[] select2 = res.getStringArray(R.array.select2);
        String[] select3 = res.getStringArray(R.array.select3);
        String[] select4 = res.getStringArray(R.array.select4);
        int[] correctAnswers = res.getIntArray(R.array.correctAnswers);
        int[] userAnswers = res.getIntArray(R.array.userAnswers);
        String[] hints = res.getStringArray(R.array.hints);
        for(int i = 1;i<=27;i++){
            if(sf.getBoolean(Integer.toString(i), false)) {
                list.add(new Quiz(numbers[i-1],questions[i-1], imageUrls[i-1], select1[i-1], select2[i-1], select3[i-1],
                        select4[i-1], correctAnswers[i-1], userAnswers[i-1], hints[i-1]));
            }
        }
        RecyclerView recyclerView = findViewById(R.id.favoriteRecyclerView);
        SolutionAdapter solutionAdapter = new SolutionAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(solutionAdapter);

    }
    public void onButtonFavoriteBackToMain(View view) { finish(); }

}