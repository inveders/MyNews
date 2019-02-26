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
import com.example.inved.mynews.searchapi.SearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewSearchAdapter extends RecyclerView.Adapter<RecyclerViewSearchAdapter.ViewHolder> {

    @Nullable
    private List<SearchResult> mData;

    RecyclerViewSearchAdapter() {
    }

    @NonNull
    @Override
    public RecyclerViewSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_general_item, parent, false);

        return new RecyclerViewSearchAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewSearchAdapter.ViewHolder holder, int position) {
      /*  if (mData == null) return;
        holder.mSectionItem.setText(mData.get(position).section);
        holder.mSubsectionItem.setText(mData.get(position).subsection);
        holder.mDateArticleItem.setText(mData.get(position).publishedDate);
        holder.mTitleItem.setText(mData.get(position).title);
        Picasso.get().load(mData.get(position).getImageUrl()).into(holder.mImageItem);*/
    }

    @Override
    public int getItemCount() {
        if (mData == null) return 0;

        return mData.size();
    }

    public void setData(List<SearchResult> data) {
        mData = data;

        //Fill the Recycler View
        notifyDataSetChanged();

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleItem;
        TextView mSectionItem;
        TextView mSubsectionItem;
        TextView mDateArticleItem;
        ImageView mImageItem;

        ViewHolder(View itemView) {

            super(itemView);

            mSectionItem = itemView.findViewById(R.id.fragment_general_item_section);
            mSubsectionItem = itemView.findViewById(R.id.fragment_general_item_subsection);
            mDateArticleItem = itemView.findViewById(R.id.fragment_general_item_date_article);
            mTitleItem = itemView.findViewById(R.id.fragment_general_item_title);
            mImageItem = itemView.findViewById(R.id.fragment_general_item_image);
        }

    }

}