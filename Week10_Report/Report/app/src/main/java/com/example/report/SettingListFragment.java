package com.example.report;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_list, container, false);
        ListView listView = view.findViewById(R.id.listViewSetting);

        String[] names = {"홍길동", "이대한", "한민국", "이윤지"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                names
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, v, position, id) ->
                Toast.makeText(requireContext(), names[position], Toast.LENGTH_SHORT).show()
        );

        return view;
    }
}