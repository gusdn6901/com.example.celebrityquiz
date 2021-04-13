package com.example.celebrityquiz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    private RadioGroup modeRadioGroup;
    private RadioGroup radioGroupTime;
    private RadioButton radioButtonModeOne;
    private RadioButton radioButtonModeTwo;
    private RadioButton radioButton30;
    private RadioButton radioButton60;
    private RadioButton radioButton90;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        modeRadioGroup = findViewById(R.id.modeRadioGroup);
        radioGroupTime = findViewById(R.id.radioGroupTime);
        // Define Level views
        radioButtonModeOne = findViewById(R.id.radioButtonModeOne);
        radioButtonModeTwo = findViewById(R.id.radioButtonModeTwo);
        radioButtonModeOne.setChecked(false);
        radioButtonModeTwo.setChecked(false);

        // Define Time views
        radioButton30 = findViewById(R.id.radioButton30);
        radioButton60 = findViewById(R.id.radioButton60);
        radioButton90 = findViewById(R.id.radioButton90);
        radioButton30.setChecked(false);
        radioButton60.setChecked(false);
        radioButton90.setChecked(false);

        SharedPreferences sf = getSharedPreferences("setting", MODE_PRIVATE);
        String mode = sf.getString("mode", "객관식");
        int seconds = sf.getInt("seconds", 60);
        switch (mode) {
            case "객관식":
                radioButtonModeOne.setChecked(true);
                break;
            case "주관식":
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
        modeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences sf = getSharedPreferences("setting", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                if(checkedId == R.id.radioButtonModeOne)
                    editor.putString("mode", "객관식");
                else if(checkedId == R.id.radioButtonModeTwo)
                    editor.putString("mode", "주관식");
                editor.commit();
            }
        });
        radioGroupTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences sf = getSharedPreferences("setting", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                if(checkedId == R.id.radioButton30)
                    editor.putInt("seconds", 301);
                else if(checkedId == R.id.radioButton60)
                    editor.putInt("seconds", 60);
                else if(checkedId == R.id.radioButton90)
                    editor.putInt("seconds", 90);
                editor.commit();
            }
        });
    }

    public void onButtonSettingsBackToMain(View view) {
        finish();
    }
}
