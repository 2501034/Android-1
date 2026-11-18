package com.example.report;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SlidePagerAdapter extends RecyclerView.Adapter<SlidePagerAdapter.SlideViewHolder> {

    private final String[] titles = {
            "1 Page", "2 Page", "3 Page", "4 Page", "5 Page", "6 Page"
    };

    private final int[] colors = {
            Color.parseColor("#A367C9"), // 보라
            Color.parseColor("#F45138"), // 주황빛 빨강
            Color.parseColor("#6B9E00"), // 올리브 그린
            Color.parseColor("#FF8C00"), // 주황
            Color.parseColor("#29A9E1"), // 하늘색
            Color.parseColor("#14C8FF")  // 밝은 시안
    };

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_slide_page, parent, false);
        return new SlideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        holder.tvPageTitle.setText(titles[position]);
        holder.pageContainer.setBackgroundColor(colors[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    static class SlideViewHolder extends RecyclerView.ViewHolder {
        LinearLayout pageContainer;
        TextView tvPageTitle;

        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            pageContainer = itemView.findViewById(R.id.pageContainer);
            tvPageTitle = itemView.findViewById(R.id.tvPageTitle);
        }
    }
}
