package com.example.midexam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        ListView listView = findViewById(R.id.listView);
        Button btnBack = findViewById(R.id.btnBack);

        // MainActivity로부터 전달받은 색상 값 수신
        Intent intent = getIntent();
        int btnBgColor = intent.getIntExtra("btnBgColor", 0xFF6200EE);
        int btnTextColor = intent.getIntExtra("btnTextColor", 0xFFFFFFFF);

        // 되돌아가기 버튼 색상 연계 반영
        btnBack.setBackgroundColor(btnBgColor);
        btnBack.setTextColor(btnTextColor);

        // res/values/array.xml 데이터 불러오기
        String[] memberArray = getResources().getStringArray(R.array.members);

        // ListView 어댑터 연결
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                memberArray
        );
        listView.setAdapter(adapter);

        // ListView 항목 클릭 이벤트 처리 (Toast 메시지 연출)
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedName = memberArray[position];
            Toast.makeText(MainActivity2.this, selectedName, Toast.LENGTH_SHORT).show();
        });

        // 되돌아가기 버튼 클릭 시 이전 화면으로 종료
        btnBack.setOnClickListener(v -> finish());

    }
}