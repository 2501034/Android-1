package com.example.project;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "눌렀네요", LENGTH_SHORT).show();

                if (flag) {
                    textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.red));
                    Toast.makeText(getBaseContext(), "눌렀네요", LENGTH_SHORT).show();
                } else {
                    textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.black));
                    Snackbar.make(v, "눌렀네요", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
                flag = !flag;
            }
        });
    }
}