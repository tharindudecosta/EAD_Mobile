package com.example.mobileapplication.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private SearchView searchView;
    private List<String> dataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize SearchView
        searchView = view.findViewById(R.id.searchView);

        // Load Data
        loadData();

        // Set up Adapter
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);

        // Set up SearchView Listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);  // Filter the RecyclerView
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);  // Filter as text changes
                return false;
            }
        });

        return view;
    }

    // Load some sample data
    private void loadData() {
        dataList = new ArrayList<>();
        dataList.add("Apple");
        dataList.add("Banana");
        dataList.add("Orange");
        dataList.add("Pineapple");
        dataList.add("Grapes");
        dataList.add("Mango");
        dataList.add("Strawberry");
        dataList.add("Blueberry");
        dataList.add("Watermelon");
        dataList.add("Papaya");
    }
}
