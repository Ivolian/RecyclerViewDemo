package com.example.administrator.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(getVerticalLinearLayoutManager());
        recyclerView.setAdapter(new MyAdapter(getDataList()));
    }

    private LinearLayoutManager getVerticalLinearLayoutManager() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }

    private List<String> getDataList() {

        List<String> dataList = new ArrayList<>();
        for (int i = 0; i != 100; i++) {
            dataList.add("item " + i);
        }

        return dataList;
    }
}
