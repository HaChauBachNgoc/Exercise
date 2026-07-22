package com.example.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.exercise.database.DBHelper;
import com.example.fitnessapp.R;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPassword, edtConfirmPassword;
    Button btnRegister;
    TextView txtBackLogin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new DBHelper(this);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        btnRegister = findViewById(R.id.btnRegister);
        txtBackLogin = findViewById(R.id.txtBackLogin);

        btnRegister.setOnClickListener(view -> {

            String name = edtName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String confirm = edtConfirmPassword.getText().toString().trim();

            if (name.isEmpty()) {
                edtName.setError("Nhập họ tên");
                return;
            }

            if (email.isEmpty()) {
                edtEmail.setError("Nhập email");
                return;
            }

            if (password.isEmpty()) {
                edtPassword.setError("Nhập mật khẩu");
                return;
            }

            if (!password.equals(confirm)) {
                edtConfirmPassword.setError("Mật khẩu không khớp");
                return;
            }

            if (dbHelper.checkEmail(email)) {

                Toast.makeText(RegisterActivity.this,
                        "Email đã tồn tại!",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            boolean result = dbHelper.insertUser(name, email, password);

            if (result) {

                Toast.makeText(RegisterActivity.this,
                        "Đăng ký thành công!",
                        Toast.LENGTH_SHORT).show();

                startActivity(new Intent(RegisterActivity.this, ProfileActivity.class));

                finish();

            } else {

                Toast.makeText(RegisterActivity.this,
                        "Đăng ký thất bại!",
                        Toast.LENGTH_SHORT).show();

            }

        });

        txtBackLogin.setOnClickListener(view -> {

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

            finish();

        });

    }
}