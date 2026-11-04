package com.example.midexam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private Button btn1, btn2, btn3;

    private int currentBtnBgColor = Color.parseColor("#0000EE"); // 파란색/보라색 계열
    private int currentBtnTextColor = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.main);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        applyButtonColors(currentBtnBgColor,currentBtnTextColor);

        Button btnSimpleListView = findViewById(R.id.button1);
        btnSimpleListView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            // 현재 설정된 색상 값 전달
            intent.putExtra("btnBgColor", currentBtnBgColor);
            intent.putExtra("btnTextColor", currentBtnTextColor);
            startActivity(intent);
        });

        Button btnSimpleRecyclerView = findViewById(R.id.button2);
        btnSimpleRecyclerView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
            intent.putExtra("btnBgColor", currentBtnBgColor);
            intent.putExtra("btnTextColor", currentBtnTextColor);
            startActivity(intent);
        });

        Button btnCustomRecyclerView = findViewById(R.id.button3);
        btnCustomRecyclerView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity4.class);
            intent.putExtra("btnBgColor", currentBtnBgColor);
            intent.putExtra("btnTextColor", currentBtnTextColor);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // 톱니바퀴 마우스 포인터 호버/롱클릭 시 "버튼 설정" 툴팁 표시
        MenuItem gearItem = menu.findItem(R.id.menu_button_setting);
        if (gearItem != null) {
            MenuItemCompat.setTooltipText(gearItem, "버튼 설정");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_button_setting) {
            View anchorView = findViewById(R.id.menu_button_setting);
            showPopupMenu(anchorView);
            return true;
        } else if (id == R.id.menu_bg_set) {
            item.setChecked(true);
            mainLayout.setBackgroundResource(R.drawable.background); // 배경 이미지 설정
            return true;
        } else if (id == R.id.menu_bg_remove) {
            item.setChecked(true);
            mainLayout.setBackground(null); // 배경 이미지 제거 (실시간 반영)
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 아이콘 하단 팝업 메뉴 표출 메서드
    private void showPopupMenu(View anchorView) {
        // Toolbar 뷰를 기준으로 PopupMenu 생성
        View view = anchorView != null ? anchorView : findViewById(R.id.toolbar);
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.color_option_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_color_bg) {
                showColorPickerDialog(true);  // 버튼 배경색 변경
                return true;
            } else if (id == R.id.menu_color_text) {
                showColorPickerDialog(false); // 버튼 글자색 변경
                return true;
            }
            return false;
        });

        popup.show();
    }


    // SeekBar 색상 조절 다이얼로그 (isBgSetting: true면 배경색, false면 글자색)
    private void showColorPickerDialog(boolean isBgSetting) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_color_picker, null);

        TextView tvTitle = dialogView.findViewById(R.id.tvDialogTitle);
        TextView tvRed = dialogView.findViewById(R.id.tvRed);
        TextView tvGreen = dialogView.findViewById(R.id.tvGreen);
        TextView tvBlue = dialogView.findViewById(R.id.tvBlue);

        SeekBar sbRed = dialogView.findViewById(R.id.sbRed);
        SeekBar sbGreen = dialogView.findViewById(R.id.sbGreen);
        SeekBar sbBlue = dialogView.findViewById(R.id.sbBlue);
        Button btnPreview = dialogView.findViewById(R.id.btnPreview);

        tvTitle.setText(isBgSetting ? "버튼 배경색을 선택해주세요" : "버튼 글자색을 선택해주세요");

        // 초기값 분기 (현재 설정된 색상 값 로드)
        int initialColor = isBgSetting ? currentBtnBgColor : currentBtnTextColor;
        int initR = Color.red(initialColor);
        int initG = Color.green(initialColor);
        int initB = Color.blue(initialColor);

        sbRed.setProgress(initR);
        sbGreen.setProgress(initG);
        sbBlue.setProgress(initB);

        // 초기 미리보기 상태 설정
        updateDialogPreview(isBgSetting, btnPreview, initR, initG, initB, tvRed, tvGreen, tvBlue);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int r = sbRed.getProgress();
                int g = sbGreen.getProgress();
                int b = sbBlue.getProgress();

                // 다이얼로그 내 버튼(btnPreview)에 실시간으로 색상 변경 반영
                updateDialogPreview(isBgSetting, btnPreview, r, g, b, tvRed, tvGreen, tvBlue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        sbRed.setOnSeekBarChangeListener(listener);
        sbGreen.setOnSeekBarChangeListener(listener);
        sbBlue.setOnSeekBarChangeListener(listener);

        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("확인", (dialog, which) -> {
                    int finalColor = Color.rgb(sbRed.getProgress(), sbGreen.getProgress(), sbBlue.getProgress());
                    if (isBgSetting) {
                        currentBtnBgColor = finalColor;
                    } else {
                        currentBtnTextColor = finalColor;
                    }
                    // 확인 버튼을 눌렀을 때 전체 메인 버튼에 최종 반영
                    applyButtonColors(currentBtnBgColor, currentBtnTextColor);
                })
                .setNegativeButton("취소", null)
                .show();
    }

    // 다이얼로그 내부 텍스트 및 미리보기 버튼 실시간 변경
    private void updateDialogPreview(boolean isBgSetting, Button btnPreview, int r, int g, int b,
                                     TextView tvR, TextView tvG, TextView tvB) {
        tvR.setText(String.format("Red : %d(%02X)", r, r));
        tvG.setText(String.format("Green : %d(%02X)", g, g));
        tvB.setText(String.format("Blue : %d(%02X)", b, b));

        int color = Color.rgb(r, g, b);
        if (isBgSetting) {
            btnPreview.setBackgroundColor(color);
            btnPreview.setTextColor(currentBtnTextColor);
        } else {
            btnPreview.setBackgroundColor(currentBtnBgColor);
            btnPreview.setTextColor(color);
        }
    }

    // 메인 화면의 모든 버튼에 배경색 및 글자색 적용
    private void applyButtonColors(int bgColor, int textColor) {
        Button[] buttons = {btn1, btn2, btn3};
        for (Button btn : buttons) {
            if (btn != null) {
                btn.setBackgroundColor(bgColor);
                btn.setTextColor(textColor);
            }
        }
    }

}