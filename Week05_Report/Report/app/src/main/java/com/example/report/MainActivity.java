package com.example.report;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvGrade;
    private final String[] grades = {"1학년", "2학년", "3학년", "4학년"};
    private int selectedIndex = -1; // 선택된 학년 인덱스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 상단 액션바 타이틀 및 보라색 배경 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Grade Selector");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6200EE")));
        }

        tvGrade = findViewById(R.id.tvGrade);

        // 텍스트뷰 클릭 시 대화상자 출력
        tvGrade.setOnClickListener(v -> showGradeDialog());
    }

    private void showGradeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 제목 설정
        builder.setTitle("몇학년 입니까?");

        // 커스텀 아이콘 적용 (res/drawable 폴더의 아이콘 파일명을 연결해주세요)
        // 만약 아이콘 리소스 이름이 ic_custom_icon 이라면 R.drawable.ic_custom_icon 로 변경하세요.
        builder.setIcon(R.drawable.grade);

        // 단일 선택 라디오 버튼 항목 설정
        builder.setSingleChoiceItems(grades, selectedIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedIndex = which;
            }
        });

        // 우측 하단 "닫기" 버튼 설정
        builder.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (selectedIndex != -1) {
                    tvGrade.setText(grades[selectedIndex]);
                }
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // "닫기" 버튼 색상 조절 (이미지와 유사한 청록색 텍스트)
        if (dialog.getButton(AlertDialog.BUTTON_POSITIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#009688"));
        }
    }
}