package com.example.administrator.recyclerviewdemo;

import android.app.Activity;
import android.content.res.Resources;
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

    private int[] colors;

    private Boolean isLinearLayout = true;

    private List<Model> modelList = null;

    private ItemViewAdapter itemViewAdapter;

    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            isLinearLayout = savedInstanceState.getBoolean("isLinearLayout");
            modelList = (List) savedInstanceState.getSerializable("modelList");
        }
        setContentView(R.layout.activity_main);
        initColors();
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(isLinearLayout ? getLinearLayoutManager() : getGridLayoutManager());
        itemViewAdapter = new ItemViewAdapter(this, modelList == null ? getModelList() : modelList);
        recyclerView.setAdapter(itemViewAdapter);

    }

    private void initSwipeRefreshLayout() {

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_blue_bright
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

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
        outState.putSerializable("modelList", (ArrayList) itemViewAdapter.getModelList());
        // 这句使得 recycler 的 position 不会因为旋转屏幕而变化。
        super.onSaveInstanceState(outState);
    }

    // ========================= functions =========================

    private void refreshList() {

        itemViewAdapter.setModelList(getModelList());
        itemViewAdapter.notifyDataSetChanged();
    }

    private void changeRecyclerViewLayout() {

        int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        recyclerView.setLayoutManager(isLinearLayout ? getGridLayoutManager() : getLinearLayoutManager());
        recyclerView.scrollToPosition(position);
        isLinearLayout = !isLinearLayout;
    }

    private LinearLayoutManager getLinearLayoutManager() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }

    private GridLayoutManager getGridLayoutManager() {

        return new GridLayoutManager(this, 2);
    }

    private List<Model> getModelList() {

        List<Model> modelList = new ArrayList<>();
        for (int i = 0; i != 20; i++) {
            modelList.add(new Model(getRandomColor(), i + 1));
        }

        return modelList;
    }

    public int getRandomColor() {

        return colors[new Random().nextInt(10)];
    }

    private void initColors() {

        final Resources resources = Resources.getSystem();
        colors = new int[]{
                resources.getColor(android.R.color.holo_blue_bright),
                resources.getColor(android.R.color.holo_blue_dark),
                resources.getColor(android.R.color.holo_blue_light),
                resources.getColor(android.R.color.holo_green_dark),
                resources.getColor(android.R.color.holo_green_light),
                resources.getColor(android.R.color.holo_orange_dark),
                resources.getColor(android.R.color.holo_orange_light),
                resources.getColor(android.R.color.holo_purple),
                resources.getColor(android.R.color.holo_red_dark),
                resources.getColor(android.R.color.holo_red_light)
        };
    }

}
