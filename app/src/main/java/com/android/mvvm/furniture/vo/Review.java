package com.android.mvvm.furniture.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {

    private String mPoster;

    public String getmPosterTitle() {
        return mPosterTitle;
    }

    private String mPosterTitle;

    public Review(String poster, String posterTitle) {
        mPoster = poster;
        mPosterTitle = posterTitle;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mPoster);
        out.writeString(mPosterTitle);
    }

    private Review(Parcel in) {
        mPoster = in.readString();
        mPosterTitle = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // After implementing the `Parcelable` interface, we need to create the
    // `Parcelable.Creator<MyParcelable> CREATOR` constant for our class;
    // Notice how it has our class specified as its type.
    public static final Parcelable.Creator<Review> CREATOR
            = new Parcelable.Creator<Review>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getmPoster() {
        return mPoster;
    }
}
