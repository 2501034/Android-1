package com.example.report;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private BroadcastReceiver dynamicReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Receiver(수신부)");

        tvResult = findViewById(R.id.tv_result);

        // 1. 동적 리시버 정의 (Toast 메시지 출력)
        dynamicReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if ("com.example.report.DYNAMIC_BROADCAST".equals(intent.getAction())) {
                    String fileName = intent.getStringExtra("fileName");
                    Toast.makeText(context, "동적 Receiver : FILE_DOWNLOADED\n" + fileName, Toast.LENGTH_SHORT).show();
                }
            }
        };

        // 동적 리시버 등록
        IntentFilter filter = new IntentFilter("com.example.report.DYNAMIC_BROADCAST");
        ContextCompat.registerReceiver(
                this,
                dynamicReceiver,
                filter,
                ContextCompat.RECEIVER_EXPORTED
        );

        // 앱이 꺼진 상태에서 정적 방송으로 실행된 경우 처리
        handleStaticIntent(getIntent());

        // MainActivity.java의 onCreate() 맨 아래에 추가
        tvResult.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 앱이 이미 실행 중인 상태에서 정적 방송을 받은 경우 처리
        handleStaticIntent(intent);
    }

    private void handleStaticIntent(Intent intent) {
        if (intent != null && intent.hasExtra("fileName")) {
            String fileName = intent.getStringExtra("fileName");
            tvResult.setText(fileName);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dynamicReceiver != null) {
            unregisterReceiver(dynamicReceiver);
        }
    }
}