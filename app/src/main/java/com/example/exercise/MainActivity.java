package com.example.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;

public class MainActivity extends AppCompatActivity {

    LinearLayout menuHome;
    LinearLayout menuDiary;
    LinearLayout menuStatistic;
    LinearLayout menuProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuHome = findViewById(R.id.menuHome);
        menuDiary = findViewById(R.id.menuDiary);
        menuStatistic = findViewById(R.id.menuStatistic);
        menuProfile = findViewById(R.id.menuProfile);

        // Thống kê
        menuStatistic.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StatisticalActivity.class);
            startActivity(intent);
        });

    }
}