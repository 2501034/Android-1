package com.example.report;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class SubActivity3 extends AppCompatActivity {

    private Button btnOpenDialog;
    private final String[] hobbies = {"독서", "등산", "수영", "수집", "영화"};
    private final boolean[] checkedHobbies = {false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);

        // 상단 액션바 제목 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("취미 입력");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6200EE")));
        }

        btnOpenDialog = findViewById(R.id.btnOpenDialog);
        btnOpenDialog.setOnClickListener(v -> showHobbyDialog());

        // Back Button 차단 및 Toast 메시지 출력 설정
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(SubActivity3.this, "Back Button을 눌렀다", Toast.LENGTH_SHORT).show();
                // super.handleOnBackPressed()를 호출하지 않아 이전 화면으로 돌아가지 않도록 막음
            }
        });
    }

    private void showHobbyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("당신의 취미는?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        // 다중 선택 체크박스 목록 설정
        builder.setMultiChoiceItems(hobbies, checkedHobbies, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedHobbies[which] = isChecked;
            }
        });

        // "닫기" 버튼 클릭 시
        builder.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ArrayList<String> selectedList = new ArrayList<>();
                for (int i = 0; i < hobbies.length; i++) {
                    if (checkedHobbies[i]) {
                        selectedList.add(hobbies[i]);
                    }
                }

                // 취미를 선택하지 않은 경우
                if (selectedList.isEmpty()) {
                    Toast.makeText(SubActivity3.this, "취미를 선택해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    // 선택된 취미 텍스트 연결 (예: "독서, 수영")
                    String resultText = String.join(", ", selectedList);

                    // 메인 액티비티로 결과 전달 후 종료
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("selected_hobbies", resultText);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

        AlertDialog dialog = builder.create();

        // Dialog에 슬라이드 애니메이션 적용
        if (dialog.getWindow() != null) {
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        }

        dialog.show();

        // "닫기" 버튼 색상 조절 (청록색 계열)
        if (dialog.getButton(AlertDialog.BUTTON_POSITIVE) != null) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#009688"));
        }
    }
}
