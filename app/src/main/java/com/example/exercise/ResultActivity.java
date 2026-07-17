package com.example.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView txtHello, txtBMI, txtCategory, txtBMR, txtGoal;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtHello = findViewById(R.id.txtHello);
        txtBMI = findViewById(R.id.txtBMI);
        txtCategory = findViewById(R.id.txtCategory);
        txtBMR = findViewById(R.id.txtBMR);
        txtGoal = findViewById(R.id.txtGoal);
        btnStart = findViewById(R.id.btnStart);

        String name = getIntent().getStringExtra("name");
        String gender = getIntent().getStringExtra("gender");
        String goal = getIntent().getStringExtra("goal");

        double height = getIntent().getDoubleExtra("height", 0);
        double weight = getIntent().getDoubleExtra("weight", 0);
        int age = getIntent().getIntExtra("age", 0);

        // BMI
        double h = height / 100.0;
        double bmi = weight / (h * h);

        String category;

        if (bmi < 18.5)
            category = "Thiếu cân";
        else if (bmi < 25)
            category = "Bình thường";
        else if (bmi < 30)
            category = "Thừa cân";
        else
            category = "Béo phì";

        // BMR
        double bmr;

        if (gender.equals("Nam")) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }

        txtHello.setText("Xin chào, " + name + "!");
        txtBMI.setText(String.format("%.1f", bmi));
        txtCategory.setText(category);
        txtBMR.setText(String.format("%.0f kcal/ngày", bmr));
        txtGoal.setText("Mục tiêu: " + goal);

        btnStart.setOnClickListener(v -> {

            Intent intent = new Intent(ResultActivity.this,
                    MainActivity.class);

            startActivity(intent);
            finish();

        });

    }
}