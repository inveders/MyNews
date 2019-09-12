
package com.example.inved.mynews.retrofit.searchapi;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Doc implements Parcelable {

    @SerializedName("web_url")
    @Expose
    public String webUrl;

    @SerializedName("multimedia")
    @Expose
    private List<Multimedium> multimedia;
    @SerializedName("headline")
    @Expose
    public Headline headline;

    @SerializedName("pub_date")
    @Expose
    public String pubDate;

    @SerializedName("news_desk")
    @Expose
    private String newsDesk;
    @SerializedName("section_name")
    @Expose
    public String sectionName;
    @SerializedName("subsectoinName")
    @Expose
    public String subsectionName;

    @SerializedName("_id")
    @Expose
    public String id;

    @SerializedName("uri")
    @Expose
    private String uri;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.webUrl);

        dest.writeList(this.multimedia);
        dest.writeParcelable(this.headline, flags);

        dest.writeString(this.pubDate);

        dest.writeString(this.newsDesk);
        dest.writeString(this.sectionName);
        dest.writeString(this.subsectionName);

        dest.writeString(this.uri);
    }


    private Doc(Parcel in) {
        this.webUrl = in.readString();

        this.multimedia = new ArrayList<>();
        in.readList(this.multimedia, Multimedium.class.getClassLoader());
        this.headline = in.readParcelable(Headline.class.getClassLoader());

        this.pubDate = in.readString();

        this.newsDesk = in.readString();
        this.sectionName = in.readString();
        this.subsectionName = in.readString();

        this.uri = in.readString();
    }

    public static final Creator<Doc> CREATOR = new Creator<Doc>() {
        @Override
        public Doc createFromParcel(Parcel source) {
            return new Doc(source);
        }

        @Override
        public Doc[] newArray(int size) {
            return new Doc[size];
        }
    };


    @NonNull
    @Override
    public String toString() {
        return "Doc{" +
                "headline=" + headline +
                ", pubDate='" + pubDate + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", subsectionName='" + subsectionName + '\'' +
                ", uri='" + uri + '\'' +

                '}';


    }

    public String getImageSearchUrl() {

        return "https://www.nytimes.com/" + multimedia.get(0).getSearchUrl();

    }
}
