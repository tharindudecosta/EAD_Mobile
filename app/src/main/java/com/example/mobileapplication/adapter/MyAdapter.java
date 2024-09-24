package com.example.mobileapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobileapplication.R;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<String> dataList;
    private List<String> filteredList;

    public MyAdapter(List<String> dataList) {
        this.dataList = dataList;
        this.filteredList = new ArrayList<>(dataList); // Copy of original data for filtering
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(filteredList.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    // Filter method for SearchView
    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(dataList);
        } else {
            for (String item : dataList) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
