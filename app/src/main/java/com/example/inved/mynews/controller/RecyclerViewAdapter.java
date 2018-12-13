package com.example.inved.mynews.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inved.mynews.R;
import com.example.inved.mynews.topstoriesapi.Result;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

   @Nullable
    private List<Result> mData;

    RecyclerViewAdapter(){}

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_general_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        if (mData==null) return ;

        holder.mTitleItem.setText(mData.get(position).title);

    }

    @Override
    public int getItemCount() {
        if(mData==null) return 0;

        return mData.size();
    }

    public void setData(List<Result> data) {
        mData = data;

        //Fill the Recycler View
        notifyDataSetChanged();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleItem;
        ImageView mImageItem;

        ViewHolder(View itemView){
            super(itemView);

            mTitleItem = itemView.findViewById(R.id.fragment_general_item_title);
            mImageItem = itemView.findViewById(R.id.fragment_general_item_image);
        }

    }
}
