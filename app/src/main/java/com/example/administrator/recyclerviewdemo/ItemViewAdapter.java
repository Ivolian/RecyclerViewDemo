package com.example.administrator.recyclerviewdemo;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ViewHolder> {

    private Activity activity;

    private List<Model> modelList;

    /**
     * 1. 持有 itemsView 中 views 的引用。
     * 2. 为 itemView 中 views 添加事件。
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        // views in itemView
        public final CardView cardView;

        public final TextView textView;

        public ViewHolder(View v) {
            super(v);

            // 1 持有引用
            cardView = (CardView) v.findViewById(R.id.cardView);
            textView = (TextView) cardView.findViewById(R.id.textView);

            // 2 添加事件
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    // We need to post a Runnable to show the popup to make sure that the PopupMenu is
                    // correctly positioned. The reason being that the view may change position before the
                    // PopupMenu is shown.
                    v.post(new Runnable() {
                        @Override
                        public void run() {

                            PopupMenu popupMenu = new PopupMenu(activity, v);
                            popupMenu.inflate(R.menu.popup);
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem) {
                                    switch (menuItem.getItemId()) {
                                        case R.id.menu_remove:
                                            int position = getPosition();
                                            ItemViewAdapter.this.notifyItemRemoved(position);
                                            modelList.remove(position);
                                            return true;
                                    }
                                    return false;
                                }
                            });
                            popupMenu.show();
                        }
                    });
                }
            });
        }
    }

    public ItemViewAdapter(Activity activity, List<Model> modelList) {

        this.activity = activity;
        this.modelList = modelList;
    }

    /**
     * 创建 itemView 时调用，每个 itemView 由一个 ViewHolder 持有引用。
     * 如果一屏幕能展示 13 itemView，那么调用该函数至多 17 次。
     * 如果一屏幕能展示 6 itemView，那么调用该函数至多 10 次。
     * 即上下各预留两个。
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view, viewGroup, false);

        return new ViewHolder(v);
    }

    /**
     * 设置(viewHolder创建时) 或替换(viewHolder复用时) itemView 中 views 的值。
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        Model model = modelList.get(position);
        viewHolder.cardView.setCardBackgroundColor(model.getColor());
        viewHolder.textView.setText(model.getNumber() + "");
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    /**
     * ViewHolder 被复用。
     */
    @Override
    public void onViewRecycled(ViewHolder viewHolder) {

        super.onViewRecycled(viewHolder);
    }

    public List<Model> getModelList() {

        return modelList;
    }

    public void setModelList(List<Model> modelList) {

        this.modelList = modelList;
    }
}
