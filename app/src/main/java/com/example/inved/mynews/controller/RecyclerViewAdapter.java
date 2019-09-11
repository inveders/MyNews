package com.example.inved.mynews.controller;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inved.mynews.MainApplication;
import com.example.inved.mynews.R;
import com.example.inved.mynews.database.MemorizedArticles;
import com.example.inved.mynews.database.MemorizedArticlesDatabase;
import com.example.inved.mynews.topstoriesapi.Result;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Set;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    @Nullable
    private List<Result> mData;
    private Set<String> mUrlMemorized;

    RecyclerViewAdapter() {

    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_general_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, final int position) {

        DateTime dt = new DateTime(mData.get(position).publishedDate);
        DateTimeFormatter displayArticleDateFormat = DateTimeFormat.forPattern("dd/MM/yy");
        String convertedPublishedDate = dt.toString(displayArticleDateFormat);

        holder.mSectionItem.setText(mData.get(position).section);
        holder.mSubsectionItem.setText(mData.get(position).subsection);
        holder.mDateArticleItem.setText(convertedPublishedDate);

        if (mData.get(position).getImageUrl() != null) {
            Picasso.get().load(mData.get(position).getImageUrl()).into(holder.mImageItem);
        } else {
            Picasso.get().load("https://pmcdeadline2.files.wordpress.com/2016/10/the-new-york-times-logo-featured.jpg?w=446&h=299&crop=1").into(holder.mImageItem);
        }

        holder.mTitleItem.setText(mData.get(position).title);
        holder.mTitleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChromeCustomTabs(view.getContext(), mData.get(position).url);
            /*    memorizedArticlesDAO = new MemorizedArticlesDAO(view.getContext());
                memorizedArticlesDAO.insertUrl(mData.get(position).url);*/
//                MemorizedArticlesDatabase.getInstance(MainApplication.getInstance().getApplicationContext()).memorizedArticlesDao().insertNewArticles(new MemorizedArticles(mData.get(position).url));
            //    verifyDaoSize();
            }

            private void verifyDaoSize() {
                if(MemorizedArticlesDatabase.getInstance(MainApplication.getInstance().getApplicationContext()).memorizedArticlesDao().getRowCount()>30){
                    for (int i = 0; i <15 ; i++) {
                        MemorizedArticlesDatabase.getInstance(MainApplication.getInstance().getApplicationContext()).memorizedArticlesDao().deleteMemorizedArticles(new MemorizedArticles(mData.get(i).url));
                    }
                }
            }
        });

        if (mUrlMemorized != null) {

            if (mUrlMemorized.contains(mData.get(position).url)) {
                // Log.d("DEBAGaa", "contenu dans la liste des url mémorisées la position est" + position);
                holder.mTitleItem.setTextColor(Color.parseColor("#8BC34A"));
            }
            else {
                holder.mTitleItem.setTextColor(Color.parseColor("#000000"));
            }
        }

    }



    @Override
    public int getItemCount() {
        if (mData == null) return 0;

        return mData.size();
    }

    void setData(List<Result> data) {
        mData = data;

        //Fill the Recycler View
        notifyDataSetChanged();

    }

    void setArticleMemorized(Set<String> urlMemorized) {
        mUrlMemorized = urlMemorized;

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

    /**
     * Creation of the Chrome Custom Tabs
     */
    private void openChromeCustomTabs(Context context, String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent intent = builder.build();
        intent.launchUrl(context, Uri.parse(url));
    }


}
