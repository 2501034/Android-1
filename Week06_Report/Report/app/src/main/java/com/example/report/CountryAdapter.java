package com.example.report;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private final ArrayList<String> countryList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String countryName);
    }

    public CountryAdapter(ArrayList<String> countryList, OnItemClickListener listener) {
        this.countryList = countryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String country = countryList.get(position);
        holder.tvCountry.setText(country);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(country);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry = itemView.findViewById(R.id.tvCountry);
        }
    }
}
