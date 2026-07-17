package com.example.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BodyActivity extends AppCompatActivity {

    EditText edtHeight, edtWeight, edtAge;
    Button btnBack, btnNext;

    String name, gender, goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        name = getIntent().getStringExtra("name");
        gender = getIntent().getStringExtra("gender");
        goal = getIntent().getStringExtra("goal");

        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        edtAge = findViewById(R.id.edtAge);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);

        btnBack.setOnClickListener(v -> finish());

        btnNext.setOnClickListener(v -> {

            String height = edtHeight.getText().toString().trim();
            String weight = edtWeight.getText().toString().trim();
            String age = edtAge.getText().toString().trim();

            if (height.isEmpty()) {
                edtHeight.setError("Nhập chiều cao");
                return;
            }

            if (weight.isEmpty()) {
                edtWeight.setError("Nhập cân nặng");
                return;
            }

            if (age.isEmpty()) {
                edtAge.setError("Nhập tuổi");
                return;
            }

            Intent intent = new Intent(
                    BodyActivity.this, ResultActivity.class);

            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("goal", goal);

            intent.putExtra("height", Double.parseDouble(height));
            intent.putExtra("weight", Double.parseDouble(weight));
            intent.putExtra("age", Integer.parseInt(age));

            startActivity(intent);

        });

    }
}