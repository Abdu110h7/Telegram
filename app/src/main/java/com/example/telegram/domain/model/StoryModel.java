package com.example.telegram.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class StoryModel implements Parcelable {

    String name;
    String storyImage;
    String storykey;
    boolean isSeen;

    public StoryModel() {
    }

    public StoryModel(String name, String storyImage, String storykey, boolean isSeen) {
        this.name = name;
        this.storyImage = storyImage;
        this.storykey = storykey;
        this.isSeen = isSeen;
    }

    protected StoryModel(Parcel in) {
        name = in.readString();
        storyImage = in.readString();
        storykey = in.readString();
        isSeen = in.readByte() != 0;
    }

    public static final Creator<StoryModel> CREATOR = new Creator<StoryModel>() {
        @Override
        public StoryModel createFromParcel(Parcel in) {
            return new StoryModel(in);
        }

        @Override
        public StoryModel[] newArray(int size) {
            return new StoryModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoryImage() {
        return storyImage;
    }

    public void setStoryImage(String storyImage) {
        this.storyImage = storyImage;
    }

    public String getStorykey() {
        return storykey;
    }

    public void setStorykey(String storykey) {
        this.storykey = storykey;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(storyImage);
        dest.writeString(storykey);
        dest.writeByte((byte) (isSeen ? 1 : 0));
    }
}
