package com.example.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.exercise.database.DBHelper;
import com.example.fitnessapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin;
    CheckBox cbRemember;
    TextView txtRegister;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper(this);

        // Ánh xạ View
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        cbRemember = findViewById(R.id.cbRemember);
        txtRegister = findViewById(R.id.txtRegister);

        // Xử lý nút Đăng nhập
        btnLogin.setOnClickListener(view -> {

            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (email.isEmpty()) {
                edtEmail.setError("Vui lòng nhập Email");
                return;
            }

            if (password.isEmpty()) {
                edtPassword.setError("Vui lòng nhập mật khẩu");
                return;
            }

            boolean result = dbHelper.checkLogin(email, password);

            if (result) {

                Toast.makeText(LoginActivity.this,
                        "Đăng nhập thành công!",
                        Toast.LENGTH_SHORT).show();

                // Chuyển thẳng sang Trang chủ (MainActivity) và xóa sạch lịch sử stack activity cũ
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            } else {

                Toast.makeText(LoginActivity.this,
                        "Email hoặc mật khẩu không đúng!",
                        Toast.LENGTH_SHORT).show();

            }

        });

        // Chuyển sang màn hình Đăng ký
        txtRegister.setOnClickListener(view -> {

            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            // Không dùng finish() ở đây để người dùng có thể bấm nút Back quay lại màn hình đăng nhập nếu muốn

        });

    }
}