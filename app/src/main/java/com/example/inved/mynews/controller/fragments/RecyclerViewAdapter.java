package com.example.inved.mynews.controller.fragments;

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
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inved.mynews.R;
import com.example.inved.mynews.database.MemorizedArticles;
import com.example.inved.mynews.models.MemorizedArticlesViewModel;
import com.example.inved.mynews.retrofit.topstoriesapi.Result;
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
    private MemorizedArticlesViewModel memorizedArticlesViewModel;

    RecyclerViewAdapter(Context context) {

        memorizedArticlesViewModel = ViewModelProviders.of((FragmentActivity) context).get(MemorizedArticlesViewModel.class);
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_general_item, parent, false);
        initData();

        return new ViewHolder(v);
    }

    private void initData() {
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, final int position) {

        if(mData!=null){
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
            holder.mTitleItem.setOnClickListener(view -> {
                openChromeCustomTabs(view.getContext(), mData.get(position).url);

                insertArticleInDatabase(mData.get(position).url);

            });

            if (mUrlMemorized != null) {

                if (mUrlMemorized.contains(mData.get(position).url)) {

                    holder.mTitleItem.setTextColor(Color.parseColor("#8BC34A"));
                } else {
                    holder.mTitleItem.setTextColor(Color.parseColor("#000000"));
                }
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

    private void insertArticleInDatabase(String url) {

        MemorizedArticles article = new MemorizedArticles(url);
        memorizedArticlesViewModel.insert(article);

    }


}
