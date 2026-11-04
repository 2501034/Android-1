package com.example.report;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button btnInput, btnResult;
    ListView listView;

    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 액션바 제목 설정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("과일 목록 관리");
        }

        btnInput = findViewById(R.id.btnInput);
        btnResult = findViewById(R.id.btnResult);
        listView = findViewById(R.id.listView);

        // 기본 과일 데이터 구성
        items = new ArrayList<>();
        items.add("사과");
        items.add("배");
        items.add("귤");
        items.add("바나나");
        items.add("복숭아");
        items.add("수박");

        // 다중 선택 체크박스 어댑터 연결
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, items);
        listView.setAdapter(adapter);

        // 1. [데이터 입력] 버튼 클릭 시 커스텀 대화상자 출력
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });

        // 2. [결과보기] 버튼 클릭 시 선택 목록 대화상자 출력
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultDialog();
            }
        });
    }

    // 과일 입력 커스텀 Dialog 출력
    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("과일 입력");

        // 동적으로 레이아웃 구성 (하늘색 배경 및 EditText 포함)
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setBackgroundColor(Color.parseColor("#4FC3F7"));
        container.setPadding(40, 40, 40, 40);

        TextView tvLabel = new TextView(this);
        tvLabel.setText("과일 이름 :");
        tvLabel.setTextSize(16);
        tvLabel.setTextColor(Color.WHITE);

        final EditText edtInput = new EditText(this);
        edtInput.setHint("과일 이름 입력해주세요");
        edtInput.setHintTextColor(Color.parseColor("#B2EBF2"));
        edtInput.setTextColor(Color.WHITE);

        container.addView(tvLabel);
        container.addView(edtInput);
        builder.setView(container);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputName = edtInput.getText().toString().trim();
                if (!inputName.isEmpty()) {
                    items.add(inputName);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "입력하였습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.show();
    }

    // 선택한 과일 목록 Dialog 출력
    private void showResultDialog() {
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < adapter.getCount(); i++) {
            if (checkedItems.get(i)) {
                sb.append(items.get(i)).append("\n");
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("선택한 과일 목록");
        builder.setMessage(sb.toString().trim());

        // 삭제 버튼 처리
        builder.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteSelectedItems();
            }
        });

        // 확인 버튼 처리
        builder.setPositiveButton("확인", null);

        builder.show();
    }

    // 선택된 항목 삭제 처리
    private void deleteSelectedItems() {
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        int count = adapter.getCount();
        int deleteCount = 0;

        // 목록 인덱스 꼬임 방지를 위한 역순 삭제
        for (int i = count - 1; i >= 0; i--) {
            if (checkedItems.get(i)) {
                items.remove(i);
                deleteCount++;
            }
        }

        listView.clearChoices();
        adapter.notifyDataSetChanged();

        if (deleteCount > 0) {
            Toast.makeText(getApplicationContext(), deleteCount + "개 삭제하였습니다", Toast.LENGTH_SHORT).show();
        }
    }
}