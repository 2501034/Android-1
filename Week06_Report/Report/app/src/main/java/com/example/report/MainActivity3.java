package com.example.report;


import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private ArrayList<String> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 상단 액션바 타이틀 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("역사 공부");
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 데이터 생성
        countryList = new ArrayList<>();
        countryList.add("신라");
        countryList.add("고구려");
        countryList.add("백제");
        countryList.add("발해");
        countryList.add("옥저");
        countryList.add("동예");
        countryList.add("부여");
        countryList.add("대한민국");

        // 어댑터 연결 및 클릭 이벤트 처리
        adapter = new CountryAdapter(countryList, countryName -> {
            Toast.makeText(getApplicationContext(), countryName + "에 대하여 알아보자", Toast.LENGTH_SHORT).show();
        });

        recyclerView.setAdapter(adapter);
    }
}