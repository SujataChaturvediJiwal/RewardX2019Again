package com.kryptoblocks.rewardx2019.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class ChildPojo implements Parcelable
{
    String child_name;

    public ChildPojo(String child_name) { this.child_name = child_name; }

    public String getChild_name() {
        return child_name;
    }

    public void setChild_name(String child_name) {
        this.child_name = child_name;
    }


    protected ChildPojo(Parcel in) {
        child_name = in.readString();
    }

    public static final Creator<ChildPojo> CREATOR = new Creator<ChildPojo>() {
        @Override
        public ChildPojo createFromParcel(Parcel in) {
            return new ChildPojo(in);
        }

        @Override
        public ChildPojo[] newArray(int size) {
            return new ChildPojo[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
