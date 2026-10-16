package com.example.report;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    private SeekBar seekBarWeight, seekBarHeight;
    private TextView tvWeight, tvHeight, tvBmiResult;

    // 몸무게 설정 (최소 35.0kg, 0.5kg 단위)
    private final float MIN_WEIGHT = 35.0f;
    private float currentWeight = 35.0f;

    // 키 설정 (최소 90.0cm, 0.5cm 단위)
    private final float MIN_HEIGHT = 90.0f;
    private float currentHeight = 90.0f;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 상단 액션바 제목 및 색상 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("BMI Calculator");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00796B")));
        }

        // 뷰 객체 참조
        seekBarWeight = findViewById(R.id.seekBarWeight);
        seekBarHeight = findViewById(R.id.seekBarHeight);
        tvWeight = findViewById(R.id.tvWeight);
        tvHeight = findViewById(R.id.tvHeight);
        tvBmiResult = findViewById(R.id.tvBmiResult);

        // 몸무게 SeekBar 리스너 (0.5kg 단위)
        seekBarWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentWeight = MIN_WEIGHT + (progress * 0.5f);
                tvWeight.setText(String.format(Locale.getDefault(), "%.1f Kg", currentWeight));
                calculateBMI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 키 SeekBar 리스너 (0.5cm 단위)
        seekBarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentHeight = MIN_HEIGHT + (progress * 0.5f);
                tvHeight.setText(String.format(Locale.getDefault(), "%.1f Cm", currentHeight));
                calculateBMI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 초기 화면 설정
        calculateBMI();
    }

    // BMI 계산 및 결과 표시
    private void calculateBMI() {
        // cm를 m 단위로 변환
        float heightInMeters = currentHeight / 100.0f;
        float bmi = currentWeight / (heightInMeters * heightInMeters);

        // 체중 상태 판정
        String category;
        if (bmi < 18.5f) {
            category = "저체중";
        } else if (bmi < 23.0f) {
            category = "정상 체중";
        } else if (bmi < 25.0f) {
            category = "과체중";
        } else {
            category = "비만";
        }

        String resultText = String.format(Locale.getDefault(), "BMI : %.2f\n당신은 %s 입니다!", bmi, category);
        tvBmiResult.setText(resultText);
    }
}