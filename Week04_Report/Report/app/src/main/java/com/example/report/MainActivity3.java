package com.example.report;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {

    private SeekBar seekBarCelsius, seekBarFahrenheit;
    private TextView tvCelsiusLabel, tvFahrenheitLabel, tvResult;
    private RadioGroup radioGroup;
    private RadioButton rbCtoF, rbFtoC;
    private Button btnConvert, btnReset;

    private float currentCelsius = 0.0f;
    private float currentFahrenheit = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 상단 액션바 타이틀 및 배경색 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Temperature Converter");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00796B")));
        }

        // 뷰 객체 연결
        seekBarCelsius = findViewById(R.id.seekBarCelsius);
        seekBarFahrenheit = findViewById(R.id.seekBarFahrenheit);
        tvCelsiusLabel = findViewById(R.id.tvCelsiusLabel);
        tvFahrenheitLabel = findViewById(R.id.tvFahrenheitLabel);
        tvResult = findViewById(R.id.tvResult);
        radioGroup = findViewById(R.id.radioGroup);
        rbCtoF = findViewById(R.id.rbCtoF);
        rbFtoC = findViewById(R.id.rbFtoC);
        btnConvert = findViewById(R.id.btnConvert);
        btnReset = findViewById(R.id.btnReset);

        // 섭씨 SeekBar 리스너 (0.5 단위)
        seekBarCelsius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentCelsius = progress * 0.5f;
                if (currentCelsius == 0) {
                    tvCelsiusLabel.setText("섭씨온도(0.0 ℃ )");
                } else {
                    tvCelsiusLabel.setText(String.format(Locale.getDefault(), "섭씨(%.1f ℃)", currentCelsius));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 화씨 SeekBar 리스너 (0.5 단위)
        seekBarFahrenheit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentFahrenheit = progress * 0.5f;
                if (currentFahrenheit == 0) {
                    tvFahrenheitLabel.setText("화씨온도(0.0 ℉)");
                } else {
                    tvFahrenheitLabel.setText(String.format(Locale.getDefault(), "화씨(%.1f ℉)", currentFahrenheit));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 변환 버튼 클릭 리스너
        btnConvert.setOnClickListener(v -> {
            int checkedId = radioGroup.getCheckedRadioButtonId();

            // 라디오 버튼을 선택하지 않은 경우
            if (checkedId == -1) {
                Toast.makeText(MainActivity3.this, "선택해주세요", Toast.LENGTH_SHORT).show();
                return;
            }

            if (checkedId == R.id.rbCtoF) {
                // 섭씨 -> 화씨 변환: F = C * 1.8 + 32
                float convertedFahrenheit = (currentCelsius * 1.8f) + 32.0f;
                String resultStr = String.format(Locale.getDefault(),
                        "섭씨온도 : %.2f ℃, 화씨온도 : %.2f ℉", currentCelsius, convertedFahrenheit);
                tvResult.setText(resultStr);
            } else if (checkedId == R.id.rbFtoC) {
                // 화씨 -> 섭씨 변환: C = (F - 32) / 1.8
                float convertedCelsius = (currentFahrenheit - 32.0f) / 1.8f;
                String resultStr = String.format(Locale.getDefault(),
                        "화씨온도 : %.2f ℉, 섭씨온도 : %.2f ℃", currentFahrenheit, convertedCelsius);
                tvResult.setText(resultStr);
            }
        });

        // 초기화 버튼 클릭 리스너
        btnReset.setOnClickListener(v -> resetAll());
    }

    // 모든 입력값을 초기 상태로 돌리는 메소드
    private void resetAll() {
        seekBarCelsius.setProgress(0);
        seekBarFahrenheit.setProgress(0);
        currentCelsius = 0.0f;
        currentFahrenheit = 0.0f;

        tvCelsiusLabel.setText("섭씨온도(0.0 ℃ )");
        tvFahrenheitLabel.setText("화씨온도(0.0 ℉)");

        radioGroup.clearCheck();
        tvResult.setText("");
    }
}