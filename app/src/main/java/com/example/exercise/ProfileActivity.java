package com.example.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    EditText edtName;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtName = findViewById(R.id.edtName);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {

            String name = edtName.getText().toString().trim();

            if(name.isEmpty()){
                edtName.setError("Nhập họ tên");
                return;
            }

            if(rgGender.getCheckedRadioButtonId() == -1){
                Toast.makeText(this,"Vui lòng chọn giới tính",Toast.LENGTH_SHORT).show();
                return;
            }

            String gender;

            if(rbMale.isChecked()){
                gender = "Nam";
            }else{
                gender = "Nữ";
            }

            Intent intent = new Intent(ProfileActivity.this, GoalActivity.class);

            intent.putExtra("name", name);
            intent.putExtra("gender", gender);

            startActivity(intent);

        });

    }
}