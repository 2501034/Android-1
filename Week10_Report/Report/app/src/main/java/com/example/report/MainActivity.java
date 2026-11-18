package com.example.report;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private LinearLayout firstLayout, secondLayout;
    private Button btnToggle;
    private ViewPager2 viewPager2, tabViewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Report02");

        firstLayout = findViewById(R.id.first);
        secondLayout = findViewById(R.id.second);
        btnToggle = findViewById(R.id.btnToggle);
        viewPager2 = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tabLayout);
        tabViewPager = findViewById(R.id.viewPager);

        // 1. 첫 번째 화면: 1~6 Page ViewPager2 설정
        SlidePagerAdapter slideAdapter = new SlidePagerAdapter();
        viewPager2.setAdapter(slideAdapter);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        // 가로/세로 슬라이드 방향 전환 버튼
        btnToggle.setOnClickListener(v -> {
            if (viewPager2.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                btnToggle.setText("세로로 슬라이드");
            } else {
                viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                btnToggle.setText("가로로 슬라이드");
            }
        });

        // 2. 두 번째 화면: TabLayout + ViewPager2 설정
        TabPagerAdapter tabAdapter = new TabPagerAdapter(this);
        tabViewPager.setAdapter(tabAdapter);

        String[] tabTitles = {"HOME", "CHATTING", "NEWS", "SETTING"};
        int[] tabIcons = {
                android.R.drawable.ic_menu_myplaces,
                android.R.drawable.sym_action_chat,
                android.R.drawable.ic_menu_sort_by_size,
                android.R.drawable.ic_menu_preferences
        };

        new TabLayoutMediator(tabLayout, tabViewPager, (tab, position) -> {
            tab.setText(tabTitles[position]);
            tab.setIcon(tabIcons[position]);
        }).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_new_slide) {
            boolean isChecked = !item.isChecked();
            item.setChecked(isChecked);

            if (isChecked) {
                firstLayout.setVisibility(View.INVISIBLE);
                secondLayout.setVisibility(View.VISIBLE);
            } else {
                firstLayout.setVisibility(View.VISIBLE);
                secondLayout.setVisibility(View.INVISIBLE);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}