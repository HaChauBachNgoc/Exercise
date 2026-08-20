package com.example.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;

public class ProfileActivity extends AppCompatActivity {

    EditText edtName, edtAge;
    RadioGroup rgGender;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Gắn với giao diện activity_profile.xml (Nhập thông tin ban đầu)

        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        rgGender = findViewById(R.id.rgGender);
        btnNext = findViewById(R.id.btnNext);

        // Lấy tên từ trang Đăng ký truyền sang (nếu có) để điền sẵn vào ô
        String regName = getIntent().getStringExtra("name");
        if (regName != null && edtName != null) {
            edtName.setText(regName);
        }

        btnNext.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String ageStr = edtAge.getText().toString().trim();

            if (name.isEmpty()) {
                edtName.setError("Vui lòng nhập tên");
                return;
            }

            if (ageStr.isEmpty()) {
                edtAge.setError("Vui lòng nhập tuổi");
                return;
            }

            int selectedId = rgGender.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
                return;
            }

            String gender = (selectedId == R.id.rbMale) ? "Nam" : "Nữ";

            try {
                int age = Integer.parseInt(ageStr);

                // Chuyển tiếp sang BodyActivity (Nhập Chiều cao, Cân nặng) và mang theo dữ liệu
                Intent intent = new Intent(ProfileActivity.this, BodyActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("gender", gender);
                intent.putExtra("age", age);
                startActivity(intent);

            } catch (NumberFormatException e) {
                edtAge.setError("Tuổi phải là số hợp lệ");
            }
        });
    }
}