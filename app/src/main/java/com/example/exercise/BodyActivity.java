package com.example.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;

public class BodyActivity extends AppCompatActivity {

    EditText edtHeight, edtWeight;
    Button btnBack, btnNext;

    String name, gender, goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        name = getIntent().getStringExtra("name");
        gender = getIntent().getStringExtra("gender");
        goal = getIntent().getStringExtra("goal");
        int age = getIntent().getIntExtra("age", 0);

        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);

        btnBack.setOnClickListener(v -> finish());

        btnNext.setOnClickListener(v -> {

            String heightStr = edtHeight.getText().toString().trim();
            String weightStr = edtWeight.getText().toString().trim();

            if (heightStr.isEmpty()) {
                edtHeight.setError("Nhập chiều cao");
                return;
            }

            if (weightStr.isEmpty()) {
                edtWeight.setError("Nhập cân nặng");
                return;
            }

            try {
                double height = Double.parseDouble(heightStr);
                double weight = Double.parseDouble(weightStr);

                Intent intent = new Intent(BodyActivity.this, ResultActivity.class);

                intent.putExtra("name", name);
                intent.putExtra("gender", gender);
                intent.putExtra("goal", goal);
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);
                intent.putExtra("age", age);

                startActivity(intent);

            } catch (NumberFormatException e) {
                edtHeight.setError("Chỉ nhập số");
                edtWeight.setError("Chỉ nhập số");
            }
        });

    }
}