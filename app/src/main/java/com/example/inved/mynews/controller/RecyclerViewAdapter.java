package com.example.inved.mynews.controller;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inved.mynews.R;
import com.example.inved.mynews.topstoriesapi.Result;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    @Nullable
    private List<Result> mData;


    RecyclerViewAdapter() {

    }



    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_general_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder,int position) {

        DateTime dt = new DateTime(mData.get(position).publishedDate);
        DateTimeFormatter displayArticleDateFormat = DateTimeFormat.forPattern("dd/MM/yy");
        String convertedPublishedDate = dt.toString(displayArticleDateFormat);
        Log.d("DEBAGa", "convertedPublishedDate "+convertedPublishedDate);


        holder.mSectionItem.setText(mData.get(position).section);
        holder.mSubsectionItem.setText(mData.get(position).subsection);
        holder.mDateArticleItem.setText(convertedPublishedDate);


        holder.mTitleItem.setText(mData.get(position).title);
        Picasso.get().load(mData.get(position).getImageUrl()).into(holder.mImageItem);

        holder.mTitleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openChromeCustomTabs(view.getContext(),"https://www.lafabriquedunet.fr/seo/articles/structure-url-performante/");

            }
        });



    }


    @Override
    public int getItemCount() {
        if (mData == null) return 0;

        return mData.size();
    }

    public void setData(List<Result> data) {
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
