package com.example.midexam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);

        TextView tvIndex = findViewById(R.id.tvDetailIndex);
        TextView tvNameAge = findViewById(R.id.tvDetailNameAge);
        TextView tvJob = findViewById(R.id.tvDetailJob);
        Button btnBack = findViewById(R.id.btnDetailBack);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 1);
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age", 0);
        String job = intent.getStringExtra("job");
        int btnBgColor = intent.getIntExtra("btnBgColor", 0xFF6200EE);
        int btnTextColor = intent.getIntExtra("btnTextColor", 0xFFFFFFFF);

        // 색상 연계 적용
        btnBack.setBackgroundColor(btnBgColor);
        btnBack.setTextColor(btnTextColor);

        // 데이터 텍스트 세팅 (나이만 빨간색 적용)
        tvIndex.setText(index + " 번째");

        String htmlText = String.format("%s (<font color='red'>%d</font> 세)", name, age);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvNameAge.setText(android.text.Html.fromHtml(htmlText, android.text.Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvNameAge.setText(android.text.Html.fromHtml(htmlText));
        }

        tvJob.setText(job);

        // 필수: 버튼 클릭 시 이전 화면으로 돌아가기 기능
        btnBack.setOnClickListener(v -> finish());



    }
}
