package com.example.administrator.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private List<String> dataList;

    /**
     * ViewHolder
     * 1. 为 itemView 中 views 添加事件。
     * 2. 持有 itemsViews 中 views 的引用。
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        // 记录多少个 ViewHolder 被创建
        public static int count = 0;

        // 每个 ViewHolder 的序号
        private int number;

        public ViewHolder(View v) {
            super(v);

            count++;
            number = count;

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("result", "view " + number + " is been clicked.");
                }
            });

            textView = (TextView) v.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public MyAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    /**
     * onCreateViewHolder
     * 创建 itemView 时调用，每个 itemView 有一个 ViewHolder 持有引用。
     * 如果一屏幕能展示 13 itemView，那么调用该函数最多 17 次。
     * 如果一屏幕能展示 6 itemView，那么调用该函数最对 10 次。
     * 即上下各预留两个。
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    /**
     * onBindViewHolder
     * 设置(viewHolder刚创建时)或替换(viewHolder复用时) itemView 中 views 的值。
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

//        Log.e("result", "onBindViewHolder " + viewHolder.number);
        viewHolder.getTextView().setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
       ViewHolder 被复用。
     */
    @Override
    public void onViewRecycled(ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
//        Log.e("result", "onViewRecycled " + viewHolder.number);
    }

}
