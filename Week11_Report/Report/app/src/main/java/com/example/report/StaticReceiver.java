package com.example.report;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StaticReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.example.report.STATIC_BROADCAST".equals(intent.getAction())) {
            String fileName = intent.getStringExtra("fileName");

            // MainActivity로 데이터를 전달하여 화면 UI 갱신
            Intent activityIntent = new Intent(context, MainActivity.class);
            activityIntent.putExtra("fileName", fileName);
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(activityIntent);
        }
    }
}
