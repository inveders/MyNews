package com.example.inved.mynews.controller;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inved.mynews.R;
import com.example.inved.mynews.searchapi.Doc;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewSearchAdapter extends RecyclerView.Adapter<RecyclerViewSearchAdapter.ViewHolder> {

    @Nullable
    private ArrayList<Doc> mData;


    RecyclerViewSearchAdapter() {
    }

    @NonNull
    @Override
    public RecyclerViewSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_general_item, parent, false);

        return new RecyclerViewSearchAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewSearchAdapter.ViewHolder holder, final int position) {

        assert mData != null;
        holder.mSectionItem.setText(mData.get(position).sectionName);
        holder.mSubsectionItem.setText(mData.get(position).subsectionName);

        DateTime dt = new DateTime(mData.get(position).pubDate);
        DateTimeFormatter displayArticleDateFormat = DateTimeFormat.forPattern("dd/MM/yy");
        String convertedPublishedDate = dt.toString(displayArticleDateFormat);
        holder.mDateArticleItem.setText(convertedPublishedDate);

        if (mData.get(position).getImageSearchUrl() != null) {

            Picasso.get().load(mData.get(position).getImageSearchUrl()).into(holder.mImageItem);
        }
        else {
            Picasso.get().load("https://pmcdeadline2.files.wordpress.com/2016/10/the-new-york-times-logo-featured.jpg?w=446&h=299&crop=1").into(holder.mImageItem);
        }

        holder.mTitleItem.setText(mData.get(position).headline.main);
        holder.mTitleItem.setOnClickListener(view -> openChromeCustomTabs(view.getContext(),mData.get(position).webUrl));


    }

    @Override
    public int getItemCount() {
        if (mData == null) return 0;

        return mData.size();
    }

    void setData(ArrayList<Doc> data) {
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

    /**Creation of the Chrome Custom Tabs*/
    private void openChromeCustomTabs (Context context, String url){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent intent = builder.build();
        intent.launchUrl(context, Uri.parse(url));
    }



}
