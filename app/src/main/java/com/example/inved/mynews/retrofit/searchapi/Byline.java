
package com.example.inved.mynews.retrofit.searchapi;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Byline implements Parcelable {

    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("person")
    @Expose
    private List<Person> person;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.original);
        dest.writeList(this.person);

    }

    private Byline(Parcel in) {
        this.original = in.readString();
        this.person = new ArrayList<>();
        in.readList(this.person, Person.class.getClassLoader());

    }

    public static final Creator<Byline> CREATOR = new Creator<Byline>() {
        @Override
        public Byline createFromParcel(Parcel source) {
            return new Byline(source);
        }

        @Override
        public Byline[] newArray(int size) {
            return new Byline[size];
        }
    };
}
