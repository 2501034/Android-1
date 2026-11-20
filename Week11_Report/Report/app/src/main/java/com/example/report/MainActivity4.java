package com.example.report;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    private final BroadcastReceiver eventReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) return;

            if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
                // 1. 전원 연결됨
                Toast.makeText(context, "전원 연결됨", Toast.LENGTH_SHORT).show();

            } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
                // 2. 전원 끊어짐
                Toast.makeText(context, "전원 끊어짐", Toast.LENGTH_SHORT).show();

            } else if (Intent.ACTION_HEADSET_PLUG.equals(action)) {
                // 3. Headset 연결 및 분리 (state 값: 1 = 연결됨, 0 = 분리됨)
                int state = intent.getIntExtra("state", -1);
                if (state == 1) {
                    Toast.makeText(context, "Headset 연결 되었습니다.", Toast.LENGTH_SHORT).show();
                } else if (state == 0) {
                    Toast.makeText(context, "Headset 분리 되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setTitle("전원과 HeadSet 감지");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 전원 연결, 전원 해제, 헤드셋 연결/분리 필터 등록
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(eventReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 화면이 가려질 때 리시버 해제
        unregisterReceiver(eventReceiver);
    }
}