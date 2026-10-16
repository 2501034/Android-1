package com.example.report;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.report.R;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar1, seekBar2;
    private TextView tvNum1, tvNum2, tvResult;
    private Button btnCalculate;

    // 선택된 자연수 저장 변수 (초기값 1)
    private int num1 = 1;
    private int num2 = 1;

    // 값 선택 여부 확인 플래그 (토스트 메시지용)
    private boolean isChanged1 = false;
    private boolean isChanged2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 상단 액션바 타이틀 설정 (GCD/LCM)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("GCD/LCM");
        }

        // 뷰 객체 참조
        seekBar1 = findViewById(R.id.seekBar1);
        seekBar2 = findViewById(R.id.seekBar2);
        tvNum1 = findViewById(R.id.tvNum1);
        tvNum2 = findViewById(R.id.tvNum2);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        // SeekBar 1 리스너 설정
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // max가 99이므로 +1을 하여 1~100 범위로 설정
                num1 = progress + 1;
                tvNum1.setText("자연수 입력(" + num1 + ")");
                if (fromUser) {
                    isChanged1 = true;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // SeekBar 2 리스너 설정
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                num2 = progress + 1;
                tvNum2.setText("자연수 입력(" + num2 + ")");
                if (fromUser) {
                    isChanged2 = true;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 버튼 클릭 이벤트 설정
        btnCalculate.setOnClickListener(v -> {
            // 두 슬라이더 중 하나라도 직접 조작하지 않은 경우 예외 메시지 출력
            if (!isChanged1 || !isChanged2) {
                Toast.makeText(MainActivity.this, "자연수를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 최대공약수 및 최소공배수 계산
            int gcd = getGCD(num1, num2);
            int lcm = (num1 * num2) / gcd;

            // 결과 텍스트 출력
            String resultText = num1 + ", " + num2 + "의 GCD(최대공약수)는 " + gcd + "입니다\n"
                    + num1 + ", " + num2 + "의 LCM(최소공배수)는 " + lcm + " 입니다.";
            tvResult.setText(resultText);
        });
    }

    // 유클리드 호제법을 이용한 최대공약수(GCD) 구하기
    private int getGCD(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}