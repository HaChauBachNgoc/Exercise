package com.example.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;

public class ResultActivity extends AppCompatActivity {

    TextView txtBMI, txtCategory, txtBMR, txtGoal;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

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
        double bmi = (h > 0) ? weight / (h * h) : 0;

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

        if (gender != null && gender.equals("Nam")) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }

        txtBMI.setText(String.format("%.1f", bmi));
        txtCategory.setText(category);
        txtBMR.setText(String.format("%.0f kcal/ngày", bmr));
        txtGoal.setText("Mục tiêu: " + (goal != null ? goal : "Duy trì cân nặng"));

        btnStart.setOnClickListener(v -> {
            // --- LƯU THÔNG TIN VÀO APPDATAMANAGER ---
            AppDataManager manager = AppDataManager.getInstance();
            if (name != null && !name.isEmpty()) {
                manager.setProfileName(name);
            }
            if (gender != null) {
                manager.setProfileGender(gender);
            }
            if (goal != null) {
                manager.setProfileGoal(goal);
            }
            manager.setProfileHeight((float) height);
            manager.setProfileAge(age);
            manager.setProfileTargetWeight((float) weight);

            // Ghi nhận cân nặng hiện tại vào lịch sử ngày hôm nay
            String todayKey = AppDataManager.getCurrentTodayKey();
            manager.setSelectedDateKey(todayKey);
            manager.setWeightForSelectedDate((float) weight);
            // ----------------------------------------

            // Chuyển thẳng đến trang Hồ sơ chính (ProfileActivity2) để hiển thị thông tin vừa lưu
            Intent intent = new Intent(ResultActivity.this, ProfileActivity2.class);
            // Xóa sạch lịch sử các Activity nhập liệu trước đó để người dùng không bấm Back quay lại được
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}