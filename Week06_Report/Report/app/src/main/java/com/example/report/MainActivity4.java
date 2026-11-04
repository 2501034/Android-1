package com.example.report;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity4 extends AppCompatActivity {

    Spinner spinnerFruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // 상단 액션바 타이틀 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("과일 목록");
            getSupportActionBar().show();
        }

        spinnerFruit = findViewById(R.id.spinnerFruit);

        // 스피너 목록 데이터 생성
        String[] fruitsArray = {"과일을 고르세요", "사과", "딸기", "수박", "바나나", "감", "복숭아", "자두"};
        ArrayList<String> fruitList = new ArrayList<>(Arrays.asList(fruitsArray));

        // 첫 번째 항목("과일을 고르세요")을 빨간색으로 표시하기 위한 커스텀 어댑터
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fruitList) {

            // 드롭다운 목록이 펼쳐질 때 각 아이템 뷰 설정
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;

                if (position == 0) {
                    // "과일을 고르세요" 텍스트만 빨간색 설정
                    textView.setTextColor(Color.RED);
                } else {
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFruit.setAdapter(adapter);

        // 스피너 항목 선택 이벤트 처리
        spinnerFruit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 0번째 "과일을 고르세요"가 선택된 경우는 Toast를 띄우지 않음
                if (position != 0) {
                    String selectedFruit = fruitList.get(position);
                    Toast.makeText(getApplicationContext(), selectedFruit + "는 맛있다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}