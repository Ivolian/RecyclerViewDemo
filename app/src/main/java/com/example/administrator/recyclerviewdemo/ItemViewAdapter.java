package com.example.administrator.recyclerviewdemo;

import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;


public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ViewHolder> {

    private static int[] colors;

    static {
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

    private List<String> dataList;

    /**
     * You can do sth in ViewHolder.
     * 1. 为 itemView 中 views 添加事件。
     * 2. 持有 itemsView 中 views 的引用。
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // 记录多少个 ViewHolder 被创建。
        public static int count = 0;

        // 每个 ViewHolder 的序号。
        public int number;

        // views in itemView
        public final CardView cardView;

        public final TextView textView;

        public ViewHolder(View v) {
            super(v);

            count++;
            number = count;

            // 1 添加事件
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // to do sth
                }
            });

            // 2 持有引用
            cardView = (CardView) v.findViewById(R.id.cardView);
            textView = (TextView) cardView.findViewById(R.id.textView);
        }

    }

    public ItemViewAdapter(List<String> dataList) {

        this.dataList = dataList;
    }

    /**
     * onCreateViewHolder
     * 创建 itemView 时调用，每个 itemView 由一个 ViewHolder 持有引用。
     * 如果一屏幕能展示 13 itemView，那么调用该函数至多 17 次。
     * 如果一屏幕能展示 6 itemView，那么调用该函数至多 10 次。
     * 即上下各预留两个。
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view, viewGroup, false);

//        Log.e("result", "onBindViewHolder " + ViewHolder.count);
        return new ViewHolder(v);
    }

    /**
     * onBindViewHolder
     * 设置(viewHolder创建时) 或替换(viewHolder复用时) itemView 中 views 的值。
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

//        Log.e("result", "onBindViewHolder " + viewHolder.number);
//        viewHolder.cardView.setCardElevation(position);
        viewHolder.cardView.setRadius(position);
        viewHolder.cardView.setCardBackgroundColor(getRandomColorValue());
        viewHolder.textView.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * ViewHolder 被复用。
     */
    @Override
    public void onViewRecycled(ViewHolder viewHolder) {

        // Log.e("result", "onViewRecycled " + viewHolder.number);
        super.onViewRecycled(viewHolder);
    }

    private int getRandomColorValue() {

        return colors[new Random().nextInt(10)];
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

}
