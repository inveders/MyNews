
package com.example.inved.mynews.retrofit.searchapi;


import android.os.Parcel;
import android.os.Parcelable;

public class Blog implements Parcelable {


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }


    private Blog() {
    }

    public static final Creator<Blog> CREATOR = new Creator<Blog>() {
        @Override
        public Blog createFromParcel(Parcel source) {
            return new Blog();
        }

        @Override
        public Blog[] newArray(int size) {
            return new Blog[size];
        }
    };
}
