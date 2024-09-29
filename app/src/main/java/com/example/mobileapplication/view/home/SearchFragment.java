package com.example.mobileapplication.view.home;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileapplication.R;
import com.example.mobileapplication.adapter.SearchQueryAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private SearchQueryAdapter adapter;
    private SearchView searchView;
    private List<String> dataList;
    private TextView searchPageTopicTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setVisibility(View.GONE);

        searchView = view.findViewById(R.id.searchView);

        searchPageTopicTv = view.findViewById(R.id.searchPageTopicTv);
        searchPageTopicTv.setVisibility(View.VISIBLE);
//        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));


        loadData();

        // Set up Adapter
        adapter = new SearchQueryAdapter(dataList);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    recyclerView.setVisibility(View.VISIBLE);
                    searchPageTopicTv.setVisibility(View.GONE);
                }
                else {
                    // Hide RecyclerView when SearchView loses focus
                    recyclerView.setVisibility(View.GONE);
                    searchPageTopicTv.setVisibility(View.VISIBLE);
                }
            }
        });

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