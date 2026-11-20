package com.example.report;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Sender(송신부)");


        Button btnDynamic = findViewById(R.id.btn_dynamic);
        Button btnStatic = findViewById(R.id.btn_static);

        // 1. 동적 BROADCASTING 버튼 클릭 시
        btnDynamic.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.report.DYNAMIC_BROADCAST");
            intent.putExtra("fileName", "Kyungbok.png");
            intent.setPackage("com.example.report");
            sendBroadcast(intent);
        });

        // 2. 정적 BROADCASTING 버튼 클릭 시
        btnStatic.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.report.STATIC_BROADCAST");
            intent.putExtra("fileName", "경복대학교.png");
            // Android 8.0(Oreo) 이상 정적 리시버 수신을 위해 수신부 패키지명 명시
            intent.setPackage("com.example.report");
            sendBroadcast(intent);
        });
    }
}