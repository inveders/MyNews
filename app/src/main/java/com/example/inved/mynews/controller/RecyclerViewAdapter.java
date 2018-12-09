package com.example.inved.mynews.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inved.mynews.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<String> results;


    RecyclerViewAdapter(List<String> results){
        this.results=results;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_general_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        /* mImageItem.setImageDrawable(response.body().results.multimedia.getImage());
           */
        holder.mTitleItem.setText(results.get(position));
        //holder.mImageItem.setImageDrawable(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
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
