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
    int age;
    double height, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        // 1. Nhận đầy đủ dữ liệu truyền từ BodyActivity sang
        name = getIntent().getStringExtra("name");
        gender = getIntent().getStringExtra("gender");
        age = getIntent().getIntExtra("age", 0);
        height = getIntent().getDoubleExtra("height", 0);
        weight = getIntent().getDoubleExtra("weight", 0);

        rgGoal = findViewById(R.id.rgGoal);
        rbLose = findViewById(R.id.rbLose);
        rbGain = findViewById(R.id.rbGain);
        rbMaintain = findViewById(R.id.rbMaintain);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);

        btnBack.setOnClickListener(v -> finish());

        // 2. Xử lý khi bấm Tiếp để chuyển sang ResultActivity
        btnNext.setOnClickListener(v -> {
            String goal = "";

            if (rbLose.isChecked()) {
                goal = "Giảm cân";
            } else if (rbGain.isChecked()) {
                goal = "Tăng cân";
            } else if (rbMaintain.isChecked()) {
                goal = "Duy trì cân nặng";
            } else {
                Toast.makeText(this, "Vui lòng chọn mục tiêu của bạn!", Toast.LENGTH_SHORT).show();
                return;
            }

            // ĐÍCH ĐẾN PHẢI LÀ ResultActivity ĐỂ TÍNH TOÁN VÀ HIỂN THỊ KẾT QUẢ
            Intent intent = new Intent(GoalActivity.this, ResultActivity.class);

            // Đóng gói toàn bộ dữ liệu mang theo đến màn hình kết quả
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("goal", goal);

            startActivity(intent);
        });
    }
}