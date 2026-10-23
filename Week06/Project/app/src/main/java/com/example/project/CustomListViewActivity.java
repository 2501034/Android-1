package com.example.project;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CustomListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        ArrayList<CustomItem> items = new ArrayList<>();
        items.add(new CustomItem(R.drawable.apple, "Apple"));
        items.add(new CustomItem(R.drawable.banana, "Banana"));
        items.add(new CustomItem(R.drawable.cherry, "Cherry"));
        items.add(new CustomItem(R.drawable.mango, "Mango"));
        items.add(new CustomItem(R.drawable.melon, "Melon"));
        items.add(new CustomItem(R.drawable.pear, "Pear"));
        items.add(new CustomItem(R.drawable.persimmon, "Persimmon"));
        items.add(new CustomItem(R.drawable.pineapple, "Pineapple"));
        items.add(new CustomItem(R.drawable.strawberry, "Strawberry"));

        ListView listView = findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(CustomListViewActivity.this, items);
        listView.setAdapter(adapter);

        EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(getBaseContext(),"과일 이릅 입혁", LENGTH_SHORT).show();
                } else {
                    items.add(new CustomItem(R.mipmap.ic_launcher, name));
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                }
            }
        });

    }
}