package com.example.celebrityquiz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    private RadioGroup radioGroupMode;
    private RadioGroup radioGroupTime;
    private RadioGroup radioGroupCategory;
    private RadioButton radioButtonModeOne;
    private RadioButton radioButtonModeTwo;
    private RadioButton radioButton30;
    private RadioButton radioButton60;
    private RadioButton radioButton90;
    private RadioButton radioButtonActor;
    private RadioButton radioButtonAthlete;
    private RadioButton radioButtonSinger;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        radioGroupMode = findViewById(R.id.radioGroupMode);
        radioGroupTime = findViewById(R.id.radioGroupTime);
        radioGroupCategory = findViewById(R.id.radioGroupCategory);

        radioButtonModeOne = findViewById(R.id.radioButtonModeOne);
        radioButtonModeTwo = findViewById(R.id.radioButtonModeTwo);
        radioButtonModeOne.setChecked(false);
        radioButtonModeTwo.setChecked(false);

        radioButton30 = findViewById(R.id.radioButton30);
        radioButton60 = findViewById(R.id.radioButton60);
        radioButton90 = findViewById(R.id.radioButton90);
        radioButton30.setChecked(false);
        radioButton60.setChecked(false);
        radioButton90.setChecked(false);

        radioButtonActor = findViewById(R.id.radioButtonActor);
        radioButtonAthlete = findViewById(R.id.radioButtonAthlete);
        radioButtonSinger = findViewById(R.id.radioButtonSinger);
        radioButtonActor.setChecked(false);
        radioButtonAthlete.setChecked(false);
        radioButtonSinger.setChecked(false);

        SharedPreferences sf = getSharedPreferences("setting", MODE_PRIVATE);
        String mode = sf.getString("mode", "?????????");
        int seconds = sf.getInt("seconds", 60);
        String category = sf.getString("category", "??????");
        switch (mode) {
            case "?????????":
                radioButtonModeOne.setChecked(true);
                break;
            case "?????????":
                radioButtonModeTwo.setChecked(true);
                break;
        }
        switch (seconds) {
            case 30:
                radioButton30.setChecked(true);
                break;
            case 60:
                radioButton60.setChecked(true);
                break;
            case 90:
                radioButton90.setChecked(true);
                break;
        }
        switch (category) {
            case "??????":
                radioButtonActor.setChecked(true);
                break;
            case "????????????":
                radioButtonAthlete.setChecked(true);
                break;
            case "??????":
                radioButtonSinger.setChecked(true);
                break;
        }
        radioGroupMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences sf = getSharedPreferences("setting", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                if(checkedId == R.id.radioButtonModeOne)
                    editor.putString("mode", "?????????");
                else if(checkedId == R.id.radioButtonModeTwo)
                    editor.putString("mode", "?????????");
                editor.commit();
            }
        });
        radioGroupTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences sf = getSharedPreferences("setting", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                if(checkedId == R.id.radioButton30)
                    editor.putInt("seconds", 30);
                else if(checkedId == R.id.radioButton60)
                    editor.putInt("seconds", 60);
                else if(checkedId == R.id.radioButton90)
                    editor.putInt("seconds", 90);
                editor.commit();
            }
        });
        radioGroupCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences sf = getSharedPreferences("setting", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                if(checkedId == R.id.radioButtonActor)
                    editor.putString("category", "??????");
                else if(checkedId == R.id.radioButtonAthlete)
                    editor.putString("category", "????????????");
                else if(checkedId == R.id.radioButtonSinger)
                    editor.putString("category", "??????");
                editor.commit();
            }
        });
    }

    public void onButtonSettingsBackToMain(View view) {
        finish();
    }
}
