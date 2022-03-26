package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initRv(){
        List<Data> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String string = String.format("mockData: %1s", i);
            list.add(new Data(string));
        }
        RecyclerView recyclerView = findViewById(R.id.rv);
        RvAdapter rvAdapter = new RvAdapter(list);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(rvAdapter);
    }
}