package com.zgmsy.dierhangdaimademo2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * class description here
 *
 * @author wzy
 * @version 1.0.0
 * @since 2017-04-25
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewHolder> {


    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class MyRecyclerViewHolder extends RecyclerView.ViewHolder
    {

        public MyRecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }
}