package com.sargisghazaryan.todo.model;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import java.util.Date;
import java.util.Objects;

public class ItemModel implements Parcelable {

    public static final int PRIORITY_MAX = 5;
    public static final int PRIORITY_MIN = 0;

    private String id;
    private String title;
    private String description;
    private String date;
    private int priority;

    public ItemModel() {
    }

    public ItemModel(String id, String title, String description, String date, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.priority = priority;
    }

    protected ItemModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        date = in.readString();
        priority = in.readInt();
    }

    public static final Creator<ItemModel> CREATOR = new Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < PRIORITY_MIN || priority > PRIORITY_MAX) {
            throw new IllegalArgumentException("Priority should be in range of " + PRIORITY_MIN
                    + " - " + PRIORITY_MAX);
        }
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        // --- Attribute mId
        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        ItemModel other = (ItemModel) obj;
        // --- Attribute mId
        if (id == null) {
            return other.id == null;
        } else {
            return id.equals(other.id);
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeInt(priority);
    }
}
