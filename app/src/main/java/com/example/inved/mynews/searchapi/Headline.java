
package com.example.inved.mynews.searchapi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headline implements Parcelable {

    @SerializedName("main")
    @Expose
    public String main;
    @SerializedName("kicker")
    @Expose
    public Object kicker;
    @SerializedName("content_kicker")
    @Expose
    public Object contentKicker;
    @SerializedName("print_headline")
    @Expose
    public String printHeadline;
    @SerializedName("name")
    @Expose
    public Object name;
    @SerializedName("seo")
    @Expose
    public Object seo;
    @SerializedName("sub")
    @Expose
    public Object sub;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.main);
        dest.writeString(this.printHeadline);

    }

    public Headline() {
    }

    protected Headline(Parcel in) {
        this.main = in.readString();
        /*this.kicker = in.readParcelable(Object.class.getClassLoader());
        this.contentKicker = in.readParcelable(Object.class.getClassLoader());*/
        this.printHeadline = in.readString();
      /*  this.name = in.readParcelable(Object.class.getClassLoader());
        this.seo = in.readParcelable(Object.class.getClassLoader());
        this.sub = in.readParcelable(Object.class.getClassLoader());*/
    }

    public static final Creator<Headline> CREATOR = new Creator<Headline>() {
        @Override
        public Headline createFromParcel(Parcel source) {
            return new Headline(source);
        }

        @Override
        public Headline[] newArray(int size) {
            return new Headline[size];
        }
    };
}
