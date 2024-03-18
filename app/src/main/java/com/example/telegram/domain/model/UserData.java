package com.example.telegram.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserData implements Parcelable {
    String name;
    String key;
    String joinedTime;
    String avatarUrl;
    String phone;
    String status;
    String lastTime;

    public UserData() {
    }

    public UserData(String name, String key, String joinedTime, String avatarUrl, String phone, String status, String lastTime) {
        this.name = name;
        this.key = key;
        this.joinedTime = joinedTime;
        this.avatarUrl = avatarUrl;
        this.phone = phone;
        this.status = status;
        this.lastTime = lastTime;
    }

    protected UserData(Parcel in) {
        name = in.readString();
        key = in.readString();
        joinedTime = in.readString();
        avatarUrl = in.readString();
        phone = in.readString();
        status = in.readString();
        lastTime = in.readString();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(String joinedTime) {
        this.joinedTime = joinedTime;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(key);
        dest.writeString(joinedTime);
        dest.writeString(avatarUrl);
        dest.writeString(phone);
        dest.writeString(status);
        dest.writeString(lastTime);
    }
}
