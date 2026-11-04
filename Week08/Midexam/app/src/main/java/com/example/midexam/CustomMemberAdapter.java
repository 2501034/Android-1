package com.example.midexam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomMemberAdapter extends RecyclerView.Adapter<CustomMemberAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(CustomMemberItem item);
    }

    private List<CustomMemberItem> itemList;
    private OnItemClickListener listener;

    public CustomMemberAdapter(List<CustomMemberItem> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomMemberItem item = itemList.get(position);

        holder.imgProfile.setImageResource(item.getImageResId());
        holder.tvName.setText(item.getName());
        holder.tvAge.setText(String.format("(%d 세)", item.getAge()));
        holder.tvJob.setText(item.getJob());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProfile;
        TextView tvName, tvAge, tvJob;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            tvName = itemView.findViewById(R.id.tvCustomName);
            tvAge = itemView.findViewById(R.id.tvCustomAge);
            tvJob = itemView.findViewById(R.id.tvCustomJob);
        }
    }
}
