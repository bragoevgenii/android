package com.example.brago.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Brago on 29.03.2017.
 */

public class StatusModel implements Parcelable {


    private String mStatus;

    public StatusModel(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    protected StatusModel(Parcel in) {
        mStatus = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mStatus);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StatusModel> CREATOR = new Parcelable.Creator<StatusModel>() {
        @Override
        public StatusModel createFromParcel(Parcel in) {
            return new StatusModel(in);
        }

        @Override
        public StatusModel[] newArray(int size) {
            return new StatusModel[size];
        }
    };
}