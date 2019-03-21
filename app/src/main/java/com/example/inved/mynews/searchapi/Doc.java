
package com.example.inved.mynews.searchapi;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.inved.mynews.utils.CollectionUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doc implements Parcelable {

    @SerializedName("web_url")
    @Expose
    public String webUrl;
    @SerializedName("snippet")
    @Expose
    public String snippet;
    @SerializedName("lead_paragraph")
    @Expose
    public String leadParagraph;
    @SerializedName("print_page")
    @Expose
    public String printPage;
    @SerializedName("blog")
    @Expose
    public Blog blog;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("multimedia")
    @Expose
    public List<Multimedium> multimedia = null;
    @SerializedName("headline")
    @Expose
    public Headline headline;
    @SerializedName("keywords")
    @Expose
    public List<Keyword> keywords = null;
    @SerializedName("pub_date")
    @Expose
    public String pubDate;
    @SerializedName("document_type")
    @Expose
    public String documentType;
    @SerializedName("news_desk")
    @Expose
    public String newsDesk;
    @SerializedName("section_name")
    @Expose
    public String sectionName;
    @SerializedName("subsectoinName")
    @Expose
    public String subsectionName;
    @SerializedName("byline")
    @Expose
    public Byline byline;
    @SerializedName("type_of_material")
    @Expose
    public String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("word_count")
    @Expose
    public Integer wordCount;
    @SerializedName("score")
    @Expose
    public Integer score;
    @SerializedName("uri")
    @Expose
    public String uri;





    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.webUrl);
      //  dest.writeString(this.snippet);
      //  dest.writeString(this.leadParagraph);
      //  dest.writeString(this.printPage);
       // dest.writeParcelable(this.blog, flags);
      //  dest.writeString(this.source);
          dest.writeList(this.multimedia);
          dest.writeParcelable(this.headline, flags);
      //  dest.writeList(this.keywords);
          dest.writeString(this.pubDate);
      //  dest.writeString(this.documentType);
          dest.writeString(this.newsDesk);
          dest.writeString(this.sectionName);
          dest.writeString(this.subsectionName);
      //  dest.writeParcelable(this.byline, flags);
      //  dest.writeString(this.typeOfMaterial);
       // dest.writeString(this.id);
      //  dest.writeValue(this.wordCount);
      //  dest.writeValue(this.score);
          dest.writeString(this.uri);
    }

    public Doc() {
    }

    protected Doc(Parcel in) {
        this.webUrl = in.readString();
      //  this.snippet = in.readString();
      //  this.leadParagraph = in.readString();
      //  this.printPage = in.readString();
      //  this.blog = in.readParcelable(Blog.class.getClassLoader());
      //  this.source = in.readString();
        this.multimedia = new ArrayList<Multimedium>();
        in.readList(this.multimedia, Multimedium.class.getClassLoader());
        this.headline = in.readParcelable(Headline.class.getClassLoader());
     //   this.keywords = new ArrayList<Keyword>();
     //   in.readList(this.keywords, Keyword.class.getClassLoader());
        this.pubDate = in.readString();
     //   this.documentType = in.readString();
        this.newsDesk = in.readString();
        this.sectionName = in.readString();
        this.subsectionName = in.readString();
    //    this.byline = in.readParcelable(Byline.class.getClassLoader());
     //   this.typeOfMaterial = in.readString();
     //   this.id = in.readString();
     //   this.wordCount = (Integer) in.readValue(Integer.class.getClassLoader());
     //   this.score = (Integer) in.readValue(Integer.class.getClassLoader());
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


    @Override
    public String toString() {
        return "Doc{" +
                "headline=" + headline +
                ", pubDate='" + pubDate + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", subsectionName='" + subsectionName + '\'' +
                ", uri='" + uri + '\'' +
                //", url image='" + multimedia + '\'' +
                '}';


    }

    public String getImageSearchUrl() {

            for (int i = 0; i <multimedia.size(); i++) {
                Log.d("DEBAGa", "Dans Doc 2 : "+multimedia.size());
                String url = "https://www.nytimes.com/"+multimedia.get(i).getSearchUrl(); /**Permet de gagner de la vitesse en gaspillant un tout petit peu de mémoire en plus(4 bytes)*/
                Log.d("DEBAGa", "Dans Doc 3 :"+url);
                if (url != null) {
                    return url;
                }
            }
        return null;
    }
}
