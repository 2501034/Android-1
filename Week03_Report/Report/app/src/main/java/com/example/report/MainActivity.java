package com.example.report;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText editId, editPassword;
    private Button btnLogin;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 상단 타이틀을 "Login"으로 변경
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Login");
        }

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        editId = findViewById(R.id.editId);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> performLogin());
    }

    private void performLogin() {
        String id = editId.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        // 아무것도 입력하지 않았거나 둘 중 하나가 빈 값인 경우
        if (id.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "데이터 입력 해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        // 아이디와 비밀번호를 하단 스낵바(Snackbar) 알림바 형태로 출력
        String message = "아이디 : " + id + " 비밀번호 : " + password;
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }
}