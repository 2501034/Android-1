package com.example.midexam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private int btnBgColor;
    private int btnTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button btnBack3 = findViewById(R.id.btnBack3);

        // 이전 액티비티에서 전달받은 버튼 색상 적용
        Intent intent = getIntent();
        btnBgColor = intent.getIntExtra("btnBgColor", 0xFF6200EE);
        btnTextColor = intent.getIntExtra("btnTextColor", 0xFFFFFFFF);

        btnBack3.setBackgroundColor(btnBgColor);
        btnBack3.setTextColor(btnTextColor);

        // res/values/array.xml 데이터 로드
        String[] names = getResources().getStringArray(R.array.members);
        String[] births = getResources().getStringArray(R.array.birth);
        String[] jobs = getResources().getStringArray(R.array.job);

        List<MemberItem> memberList = new ArrayList<>();
        int count = Math.min(names.length, Math.min(births.length, jobs.length));
        for (int i = 0; i < count; i++) {
            memberList.add(new MemberItem(names[i], Integer.parseInt(births[i]), jobs[i]));
        }

        // RecyclerView 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MemberAdapter adapter = new MemberAdapter(memberList, (position, item) -> {
            // 항목 클릭 시 상세 화면(DetailActivity)으로 이동 (실행 화면 예시 반영)
            Intent detailIntent = new Intent(MainActivity3.this, DetailActivity.class);
            detailIntent.putExtra("index", position + 1);
            detailIntent.putExtra("name", item.getName());
            detailIntent.putExtra("age", item.getAge());
            detailIntent.putExtra("job", item.getJob());
            detailIntent.putExtra("btnBgColor", btnBgColor);
            detailIntent.putExtra("btnTextColor", btnTextColor);
            startActivity(detailIntent);
        });
        recyclerView.setAdapter(adapter);

        btnBack3.setOnClickListener(v -> finish());

    }
}