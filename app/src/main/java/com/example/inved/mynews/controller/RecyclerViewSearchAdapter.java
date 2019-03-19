package com.example.inved.mynews.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inved.mynews.R;
import com.example.inved.mynews.searchapi.Doc;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewSearchAdapter extends RecyclerView.Adapter<RecyclerViewSearchAdapter.ViewHolder> {

    @Nullable
    private List<Doc> mData;

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

        holder.mSectionItem.setText(mData.get(position).sectionName);
        holder.mSubsectionItem.setText(mData.get(position).subsectionName);
        holder.mDateArticleItem.setText(mData.get(position).pubDate);
        holder.mTitleItem.setText(mData.get(position).headline.main);

        if (mData.get(position).multimedia.get(0).url != null) {
            Log.d("DEBAGa", "N'est pas nul, valeur"+mData.get(position).multimedia.get(0).url);
            Picasso.get().load(mData.get(position).multimedia.get(0).url).into(holder.mImageItem);
        }
        else {
            Log.d("DEBAGa", "Est nul");
        }

        /**Verifier que multimedia n'est pas vide*/
    }

    @Override
    public int getItemCount() {
        if (mData == null) return 0;

        return mData.size();
    }

    public void setData(List<Doc> data) {
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
