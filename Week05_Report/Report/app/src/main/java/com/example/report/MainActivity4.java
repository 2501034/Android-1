package com.example.report;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    private Button btnSpinner, btnHorizontal, btnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // 상단 액션바 제목 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Report3");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6200EE")));
        }

        btnSpinner = findViewById(R.id.btnSpinner);
        btnHorizontal = findViewById(R.id.btnHorizontal);
        btnCustom = findViewById(R.id.btnCustom);

        btnSpinner.setOnClickListener(v -> showSpinnerDialog());
        btnHorizontal.setOnClickListener(v -> showHorizontalDialog());
        btnCustom.setOnClickListener(v -> showCustomDialog());
    }

    // 1. Progress Dialog (Spinner)
    private void showSpinnerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("프로그래스 다이얼로그\n(Spinner)");
        builder.setIcon(R.drawable.baseline_key_24);

        // Spinner가 들어간 커스텀 레이아웃 연결
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_spinner, null);
        builder.setView(dialogView);

        builder.setNegativeButton("취소", (dialog, which) -> {
            Toast.makeText(MainActivity4.this, "취소하였습니다", Toast.LENGTH_SHORT).show();
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // 취소 버튼 색상 설정
        if (dialog.getButton(AlertDialog.BUTTON_NEGATIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#6200EE"));
        }
    }

    // 2. Progress Dialog (Horizontal Bar)
    private void showHorizontalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("프로그래스 다이얼로그\n(Horizontal)");
        builder.setIcon(R.drawable.baseline_key_24);

        // 커스텀 레이아웃 인플레이트
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_horizontal, null);
        builder.setView(dialogView);

        TextView tvSubMessage = dialogView.findViewById(R.id.tvProgressSubMessage);
        ProgressBar progressBar = dialogView.findViewById(R.id.progressBarHorizontal);
        TextView tvPercent = dialogView.findViewById(R.id.tvPercent);
        TextView tvNumber = dialogView.findViewById(R.id.tvNumber);

        builder.setNegativeButton("취소", (dialog, which) -> {
            Toast.makeText(MainActivity4.this, "취소하였습니다", Toast.LENGTH_SHORT).show();
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        if (dialog.getButton(AlertDialog.BUTTON_NEGATIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#6200EE"));
        }

        // 백그라운드 스레드로 프로그래스 바 진행 처리
        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {
            for (int i = 0; i <= 100; i += 2) {
                int progress = i;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(() -> {
                    progressBar.setProgress(progress);
                    tvPercent.setText(progress + "%");
                    tvNumber.setText(progress + "/100");

                    if (progress == 100) {
                        builder.setTitle("프로그래스 다이얼로그\n(Horizontal)");
                        tvSubMessage.setText("로딩이 완료되었습니다");
                        Toast.makeText(MainActivity4.this, "로딩이 완료되었습니다", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    // 3. Custom Dialog (로그인 다이얼로그)
    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("커스텀 다이얼로그");
        builder.setIcon(android.R.drawable.ic_btn_speak_now);

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_custom, null);
        builder.setView(dialogView);

        EditText etId = dialogView.findViewById(R.id.etId);
        EditText etPwd = dialogView.findViewById(R.id.etPwd);
        Button btnLogin = dialogView.findViewById(R.id.btnLogin);

        builder.setNegativeButton("취소", (dialog, which) -> dialog.dismiss());
        builder.setPositiveButton("확인", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();

        // LOGIN 버튼 클릭 시
        btnLogin.setOnClickListener(v -> {
            String id = etId.getText().toString().trim();
            String pwd = etPwd.getText().toString().trim();

            if (id.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(MainActivity4.this, "정확하게 입력해주세요", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity4.this, id + " / " + pwd, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();

        // 취소/확인 버튼 색상 설정
        if (dialog.getButton(AlertDialog.BUTTON_NEGATIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#6200EE"));
        }
        if (dialog.getButton(AlertDialog.BUTTON_POSITIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#6200EE"));
        }
    }
}