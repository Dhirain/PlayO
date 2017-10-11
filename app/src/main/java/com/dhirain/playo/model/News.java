package com.dhirain.playo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by DJ on 11-10-2017.
 */

public class News implements Parcelable {
    private Long _id;//for cupboard

    @SerializedName("created_at")
    @Expose
    private Date created_at;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("points")
    @Expose
    private int points;

    @SerializedName("num_comments")
    @Expose
    private int num_comments;

    private boolean isFavorite = false;

    public News() {
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long get_id() {
        return _id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

    public int getPoints() {
        return points;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public boolean isFavorite() {
        return isFavorite;
    }


    public void setFavorite(boolean favorite) {
        try {
            isFavorite = favorite;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "News{" +
                "_id=" + _id +
                ", created_at=" + created_at +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", author='" + author + '\'' +
                ", points=" + points +
                ", num_comments=" + num_comments +
                ", isFavorite=" + isFavorite +
                '}';
    }



    protected News(Parcel in) {
        _id = in.readByte() == 0x00 ? null : in.readLong();
        long tmpCreated_at = in.readLong();
        created_at = tmpCreated_at != -1 ? new Date(tmpCreated_at) : null;
        title = in.readString();
        url = in.readString();
        author = in.readString();
        points = in.readInt();
        num_comments = in.readInt();
        isFavorite = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (_id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(_id);
        }
        dest.writeLong(created_at != null ? created_at.getTime() : -1L);
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(author);
        dest.writeInt(points);
        dest.writeInt(num_comments);
        dest.writeByte((byte) (isFavorite ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}