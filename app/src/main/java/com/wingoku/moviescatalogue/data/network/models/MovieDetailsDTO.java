package com.wingoku.moviescatalogue.data.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetailsDTO implements Parcelable {

    @SerializedName("movie_id")
    @Expose
    private int movieId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("sub_title")
    @Expose
    private String subTitle;
    public final static Parcelable.Creator<MovieDetailsDTO> CREATOR = new Creator<MovieDetailsDTO>() {
        @SuppressWarnings({
                "unchecked"
        })
        public MovieDetailsDTO createFromParcel(Parcel in) {
            return new MovieDetailsDTO(in);
        }

        public MovieDetailsDTO[] newArray(int size) {
            return (new MovieDetailsDTO[size]);
        }
    };

    protected MovieDetailsDTO(Parcel in) {
        this.movieId = ((int) in.readValue((int.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.subTitle = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MovieDetailsDTO() {
    }

    public MovieDetailsDTO(int movieId, String title, String subTitle) {
        this.movieId = movieId;
        this.title = title;
        this.subTitle = subTitle;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(movieId);
        dest.writeValue(title);
        dest.writeValue(subTitle);
    }

    public int describeContents() {
        return 0;
    }
}