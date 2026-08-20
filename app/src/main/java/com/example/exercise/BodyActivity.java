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

    String name, gender;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        // Nhận thông tin từ ProfileActivity truyền sang
        name = getIntent().getStringExtra("name");
        gender = getIntent().getStringExtra("gender");
        age = getIntent().getIntExtra("age", 0);

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

                // ĐÍCH ĐẾN PHẢI LÀ GoalActivity ĐỂ CHỌN MỤC TIÊU CÂN NẶNG
                Intent intent = new Intent(BodyActivity.this, GoalActivity.class);

                // Mang theo toàn bộ dữ liệu cũ cộng thêm chiều cao và cân nặng vừa nhập
                intent.putExtra("name", name);
                intent.putExtra("gender", gender);
                intent.putExtra("age", age);
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);

                startActivity(intent);

            } catch (NumberFormatException e) {
                edtHeight.setError("Chỉ nhập số");
                edtWeight.setError("Chỉ nhập số");
            }
        });
    }
}