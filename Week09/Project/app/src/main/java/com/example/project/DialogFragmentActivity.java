package com.example.project;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;



public class DialogFragmentActivity extends AppCompatActivity implements View.OnClickListener {
    
    private FragmentManager manager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
        
        manager = getSupportFragmentManager();
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int type = v.getId();
        if (type == R.id.button1){
            AlertDialogFragment fragment = new AlertDialogFragment();
            fragment.show(manager, "Alert Dialog");
        } else if (type == R.id.button2) {
            ProgressDialogFragment fragment = new ProgressDialogFragment();
            fragment.show(manager, "");
        }else {
            BottomSheetDialog fragment = new BottomSheetDialog();
            fragment.show(manager,"Bottom");
        }
    }
    
}