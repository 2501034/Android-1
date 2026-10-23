package com.example.report;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private Button btnAlertDialog, btnInputDialog, btnListDialog;
    private int selectedGradeIndex = -1; // 리스트 다이얼로그 선택 상태 저장 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 액션바 제목 및 색상 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Dialog");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
        }

        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnInputDialog = findViewById(R.id.btnInputDialog);
        btnListDialog = findViewById(R.id.btnListDialog);

        // 1. 알림 다이얼로그 버튼
        btnAlertDialog.setOnClickListener(v -> showAlertDialog());

        // 2. 입력 다이얼로그 버튼
        btnInputDialog.setOnClickListener(v -> showInputDialog());

        // 3. 리스트 다이얼로그 버튼
        btnListDialog.setOnClickListener(v -> showListDialog());
    }

    // 1. 알림 다이얼로그 생성 및 표시
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("이번주 레포트는 Dialog 입니다");
        builder.setIcon(android.R.drawable.ic_dialog_alert); // 알림 아이콘

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity2.this, "확인했습니다", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // 버튼 글자 색상 (분홍색 계열)
        if (dialog.getButton(AlertDialog.BUTTON_POSITIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#E91E63"));
        }
    }

    // 2. 입력 다이얼로그 생성 및 표시
    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("이름 입력");

        // 입력창(EditText) 여백 추가 설정
        final EditText input = new EditText(this);
        FrameLayout container = new FrameLayout(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 50;
        params.rightMargin = 50;
        input.setLayoutParams(params);
        container.addView(input);
        builder.setView(container);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = input.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity2.this, "입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, name, Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // 버튼 글자 색상 (분홍색 계열)
        if (dialog.getButton(AlertDialog.BUTTON_POSITIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#E91E63"));
        }
        if (dialog.getButton(AlertDialog.BUTTON_NEGATIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#E91E63"));
        }
    }

    // 3. 리스트 다이얼로그 생성 및 표시
    private void showListDialog() {
        final String[] grades = {"1학년", "2학년", "3학년", "4학년"};
        selectedGradeIndex = -1; // 초기화

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("몇학년 입니까?");
        builder.setIcon(R.drawable.grade); // 아이콘 설정

        builder.setSingleChoiceItems(grades, selectedGradeIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedGradeIndex = which;
            }
        });

        builder.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (selectedGradeIndex != -1) {
                    String selectedGrade = grades[selectedGradeIndex];
                    Toast.makeText(MainActivity2.this, "선택한 학년 : " + selectedGrade, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "학년을 선택해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // 버튼 글자 색상 (분홍색 계열)
        if (dialog.getButton(AlertDialog.BUTTON_POSITIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#E91E63"));
        }
    }
}