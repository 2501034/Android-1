package com.example.report;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    private EditText editCel, editFah;
    private Button btnCtoF, btnFtoC;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 상단 타이틀바(액션바) 제목 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("온도 변환기");
        }

        editCel = findViewById(R.id.editCel);
        editFah = findViewById(R.id.editFah);
        btnCtoF = findViewById(R.id.btnCtoF);
        btnFtoC = findViewById(R.id.btnFtoC);
        textResult = findViewById(R.id.textResult);

        // 1. 화씨온도 계산 버튼 (섭씨 -> 화씨)
        btnCtoF.setOnClickListener(v -> {
            String input = editCel.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(MainActivity2.this, "섭씨 온도를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double cel = Double.parseDouble(input);
                double fah = (cel * 9.0 / 5.0) + 32.0;

                String resultMsg = String.format(Locale.KOREA,
                        "섭씨 온도 %.2f도는\n화씨 온도로 %.2f도 입니다.", cel, fah);
                textResult.setText(resultMsg);

                String toastMsg = String.format(Locale.KOREA, "%.2f", fah);
                Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT).show();

            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity2.this, "올바른 숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });

        // 2. 섭씨온도 계산 버튼 (화씨 -> 섭씨)
        btnFtoC.setOnClickListener(v -> {
            String input = editFah.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(MainActivity2.this, "화씨 온도를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double fah = Double.parseDouble(input);
                double cel = (fah - 32.0) * 5.0 / 9.0;

                String resultMsg = String.format(Locale.KOREA,
                        "화씨 온도 %.2f도는\n섭씨 온도로 %.2f도 입니다.", fah, cel);
                textResult.setText(resultMsg);

                String toastMsg = String.format(Locale.KOREA, "%.2f", cel);
                Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT).show();

            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity2.this, "올바른 숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}