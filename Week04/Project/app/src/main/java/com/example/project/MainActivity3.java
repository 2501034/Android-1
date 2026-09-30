package com.example.project;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    private int redProgress = 0;
    private int greenProgress = 0;
    private int blueProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView textView1 = findViewById(R.id.textView1);
        textView1.setText(String.format("%d (%2H)", redProgress, redProgress));
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(String.format("%d (%2H)", greenProgress, greenProgress));
        TextView textView3 =findViewById(R.id.textView3);
        textView3.setText(String.format("%d (%2H)", blueProgress, blueProgress));

        TextView result = findViewById(R.id.result);


        SeekBar seekBar1 = findViewById(R.id.seekbar1);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                redProgress = redProgress;
                textView1.setText(String.format("%d (%2H)", redProgress, redProgress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                redProgress = redProgress;
                textView1.setText(redProgress == 0 ? "0(00)" : String.format("%d (%2H)", redProgress, redProgress));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar seekBar2 = findViewById(R.id.seekbar2);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                greenProgress = greenProgress;
                textView1.setText(String.format("%d (%2H)", greenProgress, greenProgress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                greenProgress = greenProgress;
                textView1.setText(greenProgress == 0 ? "0(00)" : String.format("%d (%2H)", greenProgress, greenProgress));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar seekBar3 = findViewById(R.id.seekbar3);
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blueProgress = blueProgress;
                textView1.setText(String.format("%d (%2H)", blueProgress, blueProgress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                blueProgress = blueProgress;
                textView1.setText(blueProgress == 0 ? "0(00)" : String.format("%d (%2H)", blueProgress, blueProgress));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void changeColor() {
        int change = Color.rgb(redProgress,greenProgress,blueProgress);
        result.setText(
                (redProgress == 0 ? "00" : String.format("%2H", redProgress) +
                        (greenProgress == 0 ? "00" : String.format("%2H", greenProgress) +
                                (blueProgress == 0 ? "00" : String.format("%2H", blueProgress));


                String.format("%2H%2H%2H", redProgress, greenProgress, blueProgress));
        result.setBackgroundColor(change);
    }
}