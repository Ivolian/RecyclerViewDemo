package com.example.administrator.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
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

    private Boolean isLinearLayout = true;

    private ItemViewAdapter itemViewAdapter;

    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            isLinearLayout = savedInstanceState.getBoolean("isLinearLayout");
        }
        setContentView(R.layout.activity_main);
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(isLinearLayout ? getLinearLayoutManager() : getGridLayoutManager());
        itemViewAdapter = new ItemViewAdapter(getRandomDataList());
        recyclerView.setAdapter(itemViewAdapter);
    }

    private void initSwipeRefreshLayout() {

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_blue_light,
                android.R.color.holo_blue_dark
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainActivity.this.onRefresh();
            }
        });
    }

    private void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshList();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }

    // ========================= menu =========================

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
                if (!swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(true);
                    onRefresh();
                }
                return true;
            case R.id.action_change_layout:
                changeRecyclerViewLayout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // ========================= onSaveInstanceState =========================

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putBoolean("isLinearLayout", isLinearLayout);
        super.onSaveInstanceState(outState);
    }

    // ========================= level2 functions =========================

    private void refreshList() {

        itemViewAdapter.setDataList(getRandomDataList());
        itemViewAdapter.notifyDataSetChanged();
    }

    private void changeRecyclerViewLayout() {

        int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        RecyclerView.LayoutManager layoutManager = isLinearLayout ? getGridLayoutManager() : getLinearLayoutManager();
        isLinearLayout = !isLinearLayout;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.scrollToPosition(position);
    }

    // ========================= level1(basic) functions =========================

    private LinearLayoutManager getLinearLayoutManager() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }

    private GridLayoutManager getGridLayoutManager() {

        return new GridLayoutManager(this, 2);
    }

    private List<String> getRandomDataList() {

        List<String> dataList = new ArrayList<>();
        int number = new Random().nextInt(10);
        for (int i = 0; i != 40; i++) {
            dataList.add("item " + i + "." + number);
        }
        return dataList;
    }

}
