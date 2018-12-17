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
import com.example.inved.mynews.mostpopularapi.ResultMostPopular;

import java.util.List;

public class RecyclerViewAdapterMostPopular extends RecyclerView.Adapter<RecyclerViewAdapterMostPopular.ViewHolder> {

    @Nullable
    private List<ResultMostPopular> mDataMostPopular;

    public RecyclerViewAdapterMostPopular(){}

    @NonNull
    @Override
    public RecyclerViewAdapterMostPopular.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_most_popular_page_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterMostPopular.ViewHolder holder, int position) {
        if (mDataMostPopular==null) return ;

        holder.mTitleItem.setText(mDataMostPopular.get(position).title);

    }

    @Override
    public int getItemCount() {
        if(mDataMostPopular==null) return 0;

        return mDataMostPopular.size();
    }

    public void setData(List<ResultMostPopular> dataMostPopular) {
        mDataMostPopular = dataMostPopular;

        //Fill the Recycler View
        notifyDataSetChanged();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleItem;
        ImageView mImageItem;

        ViewHolder(View itemView){
            super(itemView);

            mTitleItem = itemView.findViewById(R.id.fragment_most_popular_item_title);
            mImageItem = itemView.findViewById(R.id.fragment_most_popular_item_image);
        }

    }
}
