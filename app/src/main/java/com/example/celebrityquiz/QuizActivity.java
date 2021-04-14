package com.example.celebrityquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class QuizActivity extends AppCompatActivity {

    // Declare variables
    private List<Quiz> quizList;
    private int seconds;
    private int indexCurrentQuestion;
    private String mode;
    private String category;
    private int remainTime;

    private TextView questionView;
    private ImageView imageView;
    private RadioGroup radioGroup;
    private RadioButton radioButtonOne;
    private RadioButton radioButtonTwo;
    private RadioButton radioButtonThree;
    private RadioButton radioButtonFour;
    private EditText editTextAnswer;
    private Button buttonPrevious;
    private Button buttonNext;
    private Button buttonHint;
    private TextView textTime;
    private CountDownTimer countDownTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_quiz);

        // Hide toolbar
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Define Activity views
        questionView = findViewById(R.id.celebrityQuestion);
        imageView = findViewById(R.id.celebrityImage);
        radioGroup = findViewById(R.id.celebrityOption);
        radioButtonOne = findViewById(R.id.radioButtonOne);
        radioButtonTwo = findViewById(R.id.radioButtonTwo);
        radioButtonThree = findViewById(R.id.radioButtonThree);
        radioButtonFour = findViewById(R.id.radioButtonFour);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        buttonHint = findViewById(R.id.buttonHint);
        textTime = findViewById(R.id.textTime);
        // Define button views
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrevious = findViewById(R.id.buttonPrevious);

        SharedPreferences sf = getSharedPreferences("setting", MODE_PRIVATE);
        mode = sf.getString("mode", "객관식");
        seconds = sf.getInt("seconds", 60);
        category = sf.getString("category", "배우");

        if(mode.equals("객관식"))
            editTextAnswer.setVisibility(View.GONE);
        else if(mode.equals("주관식"))
            radioGroup.setVisibility(View.GONE);

        // setOnClickListener and set checked onClick for each button
        radioButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RadioButton) view).setChecked(true);
                quizList.get(indexCurrentQuestion).userAnswer = 1;
            }
        });

        radioButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RadioButton) view).setChecked(true);
                quizList.get(indexCurrentQuestion).userAnswer = 2;
            }
        });

        radioButtonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RadioButton) view).setChecked(true);
                quizList.get(indexCurrentQuestion).userAnswer = 3;
            }
        });

        radioButtonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RadioButton) view).setChecked(true);
                quizList.get(indexCurrentQuestion).userAnswer = 4;
            }
        });

        Intent intent = getIntent();
        int stage = intent.getIntExtra("stage", 1);

        int iter = 0;
        switch (category) {
            case "배우":
                break;
            case "운동선수":
                iter = 9;
                break;
            case "가수":
                iter = 18;
                break;
        }
        switch (stage) {
            case 1:
                break;
            case 2:
                iter += 3;
                break;
            case 3:
                iter += 6;
                break;
        }
        int end = iter + 3;

        ArrayList<Quiz> list = new ArrayList<>();
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
        final String[] hints = res.getStringArray(R.array.hints);
        for(; iter < end; iter++) {
            list.add(new Quiz(numbers[iter],questions[iter], imageUrls[iter], select1[iter], select2[iter], select3[iter],
                    select4[iter], correctAnswers[iter], userAnswers[iter], hints[iter]));
        }
        quizList = list;
        System.out.println(imageUrls[0]);
        buttonHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hint = hints[indexCurrentQuestion];
                Toast myToast = Toast.makeText(QuizActivity.this, hint, Toast.LENGTH_LONG);
                myToast.show();
                myToast.setGravity(Gravity.TOP, 300, 200);

                buttonHint.setVisibility(View.INVISIBLE);
            }
        });
//        // Access intent interface and get variables
//        Intent intent = getIntent();
//        int level = intent.getIntExtra("level", 0);
//        seconds = intent.getIntExtra("seconds", 30);
//        String string = null;
//
//        //Safely read data from saved file
//        try {
//            FileInputStream fileInputStream = openFileInput("myJson");
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            StringBuilder stringBuilder = new StringBuilder();
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//            string = stringBuilder.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<Quiz>>(){}.getType();
//        List<Quiz> list = gson.fromJson(string, type);
//
//        // Set sublist based on user set level
//        if (level == 1) {
//            assert list != null;
//            quizList = list.subList(0, 5);
//        } else if (level == 2) {
//            assert list != null;
//            quizList = list.subList(5, 10);
//        } else {
//            assert list != null;
//            quizList = list.subList(10, 15);
//        }

        // initialise and set for each index in current activity as current question
        indexCurrentQuestion = 0;
        Quiz currentQuestion = quizList.get(indexCurrentQuestion);
        currentQuestionView(currentQuestion);
        buttonPrevious.setEnabled(false); // Disable previous button when current index is 0

        // See function
        startTimer();

        // When user submit quiz, stop time and start Solution Activity
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
                doFinishTask();
            }
        });
    }

    // Start countdown. OnFinish, start Solution Activity
    public void startTimer() {
        textTime.setText(String.valueOf(seconds));
        countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textTime.setText(String.valueOf((int) (millisUntilFinished / 1000)));
                remainTime = (int)(millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                doFinishTask();
            }
        }.start();
    }

    // Cancel timer to prevent countDown in background
    // If not defined, Solution Activity will start even when user goes back to
    // Main Activity because Quiz Activity doesn't get destroyed instantly
    public void stopTimer() {
        countDownTimer.cancel();
    }

    // Pre-define new views before setting previous question as current question, for index < 0
    public void onButtonPrevious(View view) {
        if(indexCurrentQuestion != 0) {
            indexCurrentQuestion--;
            if(indexCurrentQuestion == 0) buttonPrevious.setEnabled(false);
            if(indexCurrentQuestion != (quizList.size() - 1)) buttonNext.setEnabled(true);
            Quiz currentQuestion = quizList.get(indexCurrentQuestion);
            quizList.get(indexCurrentQuestion+1).userTextAnswer = editTextAnswer.getText().toString();
            currentQuestionView(currentQuestion);

            editTextAnswer.setText(currentQuestion.userTextAnswer);
            radioGroup = findViewById(R.id.celebrityOption);
            if(currentQuestion.userAnswer == 0) radioGroup.clearCheck();
            else {
                switch (currentQuestion.userAnswer) {
                    case 1: {
                        radioGroup.check(R.id.radioButtonOne);
                        break;
                    }
                    case 2: {
                        radioGroup.check(R.id.radioButtonTwo);
                        break;
                    }
                    case 3: {
                        radioGroup.check(R.id.radioButtonThree);
                        break;
                    }
                    case 4: {
                        radioGroup.check(R.id.radioButtonFour);
                        break;
                    }
                }
            }
        }
    }

    // Pre-define new views before setting next question as current question, for index > list.size()
    public void onButtonNext(View view) {
        if(indexCurrentQuestion != (quizList.size() - 1)) {
            indexCurrentQuestion++;
            if(indexCurrentQuestion == (quizList.size() - 1)) buttonNext.setEnabled(false);
            if(indexCurrentQuestion != 0) buttonPrevious.setEnabled(true);
            Quiz currentQuestion = quizList.get(indexCurrentQuestion);
            quizList.get(indexCurrentQuestion-1).userTextAnswer = editTextAnswer.getText().toString();
            currentQuestionView(currentQuestion);

            editTextAnswer.setText(currentQuestion.userTextAnswer);
            radioGroup = findViewById(R.id.celebrityOption);
            if(currentQuestion.userAnswer == 0) radioGroup.clearCheck();
            else {
                switch (currentQuestion.userAnswer) {
                    case 1: {
                        radioGroup.check(R.id.radioButtonOne);
                        break;
                    }
                    case 2: {
                        radioGroup.check(R.id.radioButtonTwo);
                        break;
                    }
                    case 3: {
                        radioGroup.check(R.id.radioButtonThree);
                        break;
                    }
                    case 4: {
                        radioGroup.check(R.id.radioButtonFour);
                        break;
                    }
                }
            }
        }
    }

    public void currentQuestionView(Quiz currentQuestion) {
        questionView.setText(String.format("%s. %s", indexCurrentQuestion + 1, currentQuestion.question));
        radioButtonOne.setText(currentQuestion.one);
        radioButtonTwo.setText(currentQuestion.two);
        radioButtonThree.setText(currentQuestion.three);
        radioButtonFour.setText(currentQuestion.four);
        editTextAnswer.setText(currentQuestion.userTextAnswer);
        Glide.with(imageView.getContext()).load(currentQuestion.imageUrl).into(imageView);
    }

    // Calculate score
    public int getScore() {
        int score = 0;
        SharedPreferences sf = getSharedPreferences("setting", MODE_PRIVATE);

        if (mode.equals("객관식")) {
            for (int i = 0; i < quizList.size(); i++)
                if (quizList.get(i).userAnswer == quizList.get(i).correctAnswer) score++;
        } else if (mode.equals("주관식")) {
            for (int i = 0; i < quizList.size(); i++) {
                String answerString = "";
                switch (quizList.get(i).correctAnswer) {
                    case 1:
                        answerString = quizList.get(i).one;
                        break;
                    case 2:
                        answerString = quizList.get(i).two;
                        break;
                    case 3:
                        answerString = quizList.get(i).three;
                        break;
                    case 4:
                        answerString = quizList.get(i).four;
                        break;
                }
                if(answerString.equals(quizList.get(i).userTextAnswer)) score++;
            }
        }
        return score;
    }

    public void onButtonStar(View view){
        SharedPreferences sf = getSharedPreferences("starboolean",MODE_PRIVATE);
        boolean starchecked = sf.getBoolean(Integer.toString(quizList.get(indexCurrentQuestion).number), false);

        SharedPreferences.Editor editor = sf.edit();
        if(starchecked){
            editor.putBoolean(Integer.toString(quizList.get(indexCurrentQuestion).number), false);
        }
        else{
            editor.putBoolean(Integer.toString(quizList.get(indexCurrentQuestion).number), true);
        }
        editor.commit();
    }

    private void doFinishTask() {
        SharedPreferences sf;
        if(mode.equals("객관식")) sf = getSharedPreferences("Log_mode1", MODE_PRIVATE);
        else sf = getSharedPreferences("Log_mode2", MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        int score = getScore();

        int addLogLocation = sf.getInt("size", 0)+1;

        editor.putInt("size", addLogLocation);
        editor.putString("category"+addLogLocation, category);
        editor.putString("stage"+ addLogLocation, "스테이지" + getIntent().getIntExtra("stage", 1));
        editor.putString("score"+ addLogLocation, score + "개");
        editor.putString("seconds"+ addLogLocation, (seconds - remainTime) + "초");
        editor.commit();

        finish();
        Intent i = new Intent(QuizActivity.this, SolutionActivity.class);
        i.putExtra("score", score);
        // Change List to ArrayList to accommodate subList
        ArrayList<Quiz> list = new ArrayList<>(quizList);
        i.putExtra("quizList", list);
        i.setFlags(Intent. FLAG_ACTIVITY_CLEAR_TOP | Intent. FLAG_ACTIVITY_SINGLE_TOP );
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopTimer();
    }
}
