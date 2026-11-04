package com.example.midexam;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.customRecyclerView);
        Button btnBack4 = findViewById(R.id.btnBack4);

        // 이전 액티비티 색상 적용 연계
        Intent intent = getIntent();
        int btnBgColor = intent.getIntExtra("btnBgColor", 0xFF6200EE);
        int btnTextColor = intent.getIntExtra("btnTextColor", 0xFFFFFFFF);

        btnBack4.setBackgroundColor(btnBgColor);
        btnBack4.setTextColor(btnTextColor);

        // person1 ~ person5 이미지 리소스 배열
        int[] images = {
                R.drawable.person1,
                R.drawable.person2,
                R.drawable.person3,
                R.drawable.person4,
                R.drawable.person5
        };

        // array.xml 로드
        String[] names = getResources().getStringArray(R.array.members);
        String[] births = getResources().getStringArray(R.array.birth);
        String[] jobs = getResources().getStringArray(R.array.job);

        List<CustomMemberItem> itemList = new ArrayList<>();
        int count = Math.min(names.length, Math.min(births.length, jobs.length));

        for (int i = 0; i < count; i++) {
            // person1~5 번갈아 적용 (% images.length)
            int imgRes = images[i % images.length];
            itemList.add(new CustomMemberItem(imgRes, names[i], Integer.parseInt(births[i]), jobs[i]));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomMemberAdapter adapter = new CustomMemberAdapter(itemList, item -> {
            // 항목 클릭 시 AlertDialog 팝업 표시
            showMemberDialog(item);
        });
        recyclerView.setAdapter(adapter);

        // 되돌아가기
        btnBack4.setOnClickListener(v -> finish());
    }

    // 선택한 회원 AlertDialog 생성 함수
    private void showMemberDialog(CustomMemberItem item) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_custom_member, null);

        ImageView img = dialogView.findViewById(R.id.dialogImg);
        TextView tvNameAge = dialogView.findViewById(R.id.dialogTvNameAge);
        TextView tvJob = dialogView.findViewById(R.id.dialogTvJob);

        img.setImageResource(item.getImageResId());

        // 나이 숫자만 빨간색 처리
        String htmlText = String.format("%s (<font color='red'>%d</font> 세)", item.getName(), item.getAge());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvNameAge.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvNameAge.setText(Html.fromHtml(htmlText));
        }

        tvJob.setText(item.getJob());

        new AlertDialog.Builder(this)
                .setTitle("선택한 회원")
                .setView(dialogView)
                .setPositiveButton("확인", null)
                .show();

    }
}