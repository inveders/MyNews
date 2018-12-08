package com.example.inved.mynews.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inved.mynews.R;
import com.example.inved.mynews.model.topstories.Result;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Result> results;

    RecyclerViewAdapter(List<Result> results){
        this.results=results;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_general_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.display(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder {

        private TextView mTitleItem;
        private ImageView mImageItem;

        MyViewHolder(View itemView){
            //super(itemView);

            mTitleItem = itemView.findViewById(R.id.fragment_general_item_title);
            mImageItem = itemView.findViewById(R.id.fragment_general_item_image);
        }

        /*void display(Jeuvideo jeuvideo){
            mTitleItem.setText(jeuvideo.getTitle());
            mImageItem.setImageDrawable(jeuvideo.getImage());

        }*/
    }
}
