package com.example.report;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private TextView tvHobby;

    // SubActivity3로부터 전달받은 결과를 처리하는 Launcher
    private final ActivityResultLauncher<Intent> hobbyLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String selectedHobbies = result.getData().getStringExtra("selected_hobbies");
                    if (selectedHobbies != null && !selectedHobbies.isEmpty()) {
                        tvHobby.setText(selectedHobbies);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 상단 액션바 제목 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Hobby");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6200EE")));
        }

        tvHobby = findViewById(R.id.tvHobby);

        // 텍스트뷰 클릭 시 별도의 Activity(SubActivity3)로 이동
        tvHobby.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, SubActivity3.class);
            hobbyLauncher.launch(intent);
        });
    }
}