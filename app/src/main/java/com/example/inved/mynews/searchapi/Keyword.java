
package com.example.inved.mynews.searchapi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keyword implements Parcelable {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("value")
    @Expose
    public String value;
    @SerializedName("rank")
    @Expose
    public Integer rank;
    @SerializedName("major")
    @Expose
    public String major;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.value);
        dest.writeValue(this.rank);
        dest.writeString(this.major);
    }

    public Keyword() {
    }

    protected Keyword(Parcel in) {
        this.name = in.readString();
        this.value = in.readString();
        this.rank = (Integer) in.readValue(Integer.class.getClassLoader());
        this.major = in.readString();
    }

    public static final Creator<Keyword> CREATOR = new Creator<Keyword>() {
        @Override
        public Keyword createFromParcel(Parcel source) {
            return new Keyword(source);
        }

        @Override
        public Keyword[] newArray(int size) {
            return new Keyword[size];
        }
    };
}
