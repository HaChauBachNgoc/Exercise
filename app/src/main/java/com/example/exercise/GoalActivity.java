package com.example.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;

public class GoalActivity extends AppCompatActivity {

    RadioGroup rgGoal;
    RadioButton rbLose, rbGain, rbMaintain;
    Button btnBack, btnNext;

    String name, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        name = getIntent().getStringExtra("name");
        gender = getIntent().getStringExtra("gender");
        int age = getIntent().getIntExtra("age", 0);

        rgGoal = findViewById(R.id.rgGoal);
        rbLose = findViewById(R.id.rbLose);
        rbGain = findViewById(R.id.rbGain);
        rbMaintain = findViewById(R.id.rbMaintain);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);

        btnBack.setOnClickListener(v -> finish());

        btnNext.setOnClickListener(v -> {

            String goal = "";

            if (rbLose.isChecked()) {
                goal = "Giảm cân";
            } else if (rbGain.isChecked()) {
                goal = "Tăng cân";
            } else if (rbMaintain.isChecked()) {
                goal = "Duy trì cân nặng";
            }



            Intent intent = new Intent(GoalActivity.this, BodyActivity.class);

            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("goal", goal);
            intent.putExtra("age", age);

            startActivity(intent);
        });

    }
}