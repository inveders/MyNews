
package com.example.inved.mynews.searchapi;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Byline implements Parcelable {

    @SerializedName("original")
    @Expose
    public String original;
    @SerializedName("person")
    @Expose
    public List<Person> person = null;
    @SerializedName("organization")
    @Expose
    public Object organization;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.original);
        dest.writeList(this.person);

    }

    public Byline() {
    }

    protected Byline(Parcel in) {
        this.original = in.readString();
        this.person = new ArrayList<Person>();
        in.readList(this.person, Person.class.getClassLoader());
        this.organization = in.readParcelable(Object.class.getClassLoader());
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
