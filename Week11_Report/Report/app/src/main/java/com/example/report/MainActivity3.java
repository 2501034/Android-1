package com.example.report;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private TextView tvCount, tvPlug, tvStatus, tvPercent;
    private ProgressBar pbBattery;
    private int receiveCount = 0;

    private final BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                // 1. 수신 회수 증가 및 표시
                receiveCount++;
                tvCount.setText("수신 회수 : " + receiveCount);

                // 2. 전원 연결 상태 확인 (Battery, AC, USB, Wireless)
                int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                String plugText;
                switch (plugged) {
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        plugText = "AC";
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        plugText = "USB";
                        break;
                    case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                        plugText = "Wireless";
                        break;
                    case 0:
                    default:
                        plugText = "Battery";
                        break;
                }
                tvPlug.setText("연결 : " + plugText);

                // 3. 배터리 상태 확인
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
                String statusText;
                switch (status) {
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        statusText = "충전 중";
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        statusText = "방전 중";
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        statusText = "충전 안 함";
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        statusText = "완충됨";
                        break;
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    default:
                        statusText = "알 수 없음";
                        break;
                }
                tvStatus.setText("상태 : " + statusText);

                // 4. 충전 비율(%) 계산 및 ProgressBar 반영
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int batteryPct = (int) ((level / (float) scale) * 100);

                pbBattery.setProgress(batteryPct);
                tvPercent.setText(batteryPct + " %");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("Battery 상태확인");

        tvCount = findViewById(R.id.tv_count);
        tvPlug = findViewById(R.id.tv_plug);
        tvStatus = findViewById(R.id.tv_status);
        pbBattery = findViewById(R.id.pb_battery);
        tvPercent = findViewById(R.id.tv_percent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 화면이 보일 때 배터리 방송 리시버 등록
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 화면이 가려질 때 리시버 해제
        unregisterReceiver(batteryReceiver);
    }
}