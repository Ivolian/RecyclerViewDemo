package com.example.administrator.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends Activity {

    private MyAdapter myAdapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView() {

         recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(getLinearLayoutManager());
        myAdapter = new MyAdapter(getDataList());
        recyclerView.setAdapter(myAdapter);
    }

    private LinearLayoutManager getLinearLayoutManager() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }

    private GridLayoutManager getGridLayoutManager() {

        return new GridLayoutManager(this, 2);
    }

    private List<String> getDataList() {

        List<String> dataList = new ArrayList<>();
        int number = new Random().nextInt(10);
        for (int i = 0; i != 20; i++) {
            dataList.add("item " + number);
        }
        return dataList;
    }

    private void refreshDataList() {

        myAdapter.setDataList(getDataList());
        recyclerView.swapAdapter(myAdapter,false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_refresh:
                refreshDataList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
