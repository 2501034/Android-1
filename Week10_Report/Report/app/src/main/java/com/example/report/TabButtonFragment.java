package com.example.report;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabButtonFragment extends Fragment {

    private static final String ARG_TEXT = "arg_text";
    private static final String ARG_COLOR = "arg_color";

    public static TabButtonFragment newInstance(String buttonText, String bgColor) {
        TabButtonFragment fragment = new TabButtonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, buttonText);
        args.putString(ARG_COLOR, bgColor);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_button, container, false);
        LinearLayout root = view.findViewById(R.id.tabRootLayout);
        Button btnTab = view.findViewById(R.id.btnTab);

        if (getArguments() != null) {
            btnTab.setText(getArguments().getString(ARG_TEXT));
            root.setBackgroundColor(Color.parseColor(getArguments().getString(ARG_COLOR)));
        }
        return view;
    }
}