package com.example.report;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtName;
    Button btnInsert, btnDelete, btnResult;
    ListView listView;

    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 액션바 타이틀 지정
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("회원 명단 관리");
        }

        edtName = findViewById(R.id.edtName);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnResult = findViewById(R.id.btnResult);
        listView = findViewById(R.id.listView);

        // 기본 데이터
        items = new ArrayList<>();
        items.add("홍길동");
        items.add("이대한");
        items.add("대한민국");

        // 커스텀 레이아웃(R.layout.list_item) 연결
        adapter = new ArrayAdapter<>(this, R.layout.list_item, items);
        listView.setAdapter(adapter);

        // 1. 입력 버튼 클릭 시
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                if (!name.isEmpty()) {
                    items.add(name);
                    adapter.notifyDataSetChanged();
                    edtName.setText("");
                }
            }
        });

        // 2. 리스트 항목 클릭 시 Toast 표시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = items.get(position);
                Toast.makeText(getApplicationContext(), "선택 : " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });

        // 3. 삭제 버튼 클릭 시
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedItems();
            }
        });

        // 4. 결과보기 버튼 클릭 시
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectedDialog();
            }
        });
    }

    // 선택된 항목 삭제 처리
    private void deleteSelectedItems() {
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        int count = adapter.getCount();
        int deleteCount = 0;

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

    // 선택 목록 Dialog 출력
    private void showSelectedDialog() {
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < adapter.getCount(); i++) {
            if (checkedItems.get(i)) {
                sb.append(items.get(i)).append("\n");
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("선택한 회원 목록");
        builder.setMessage(sb.toString().trim());

        builder.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteSelectedItems();
            }
        });

        builder.setPositiveButton("확인", null);

        builder.show();
    }
}