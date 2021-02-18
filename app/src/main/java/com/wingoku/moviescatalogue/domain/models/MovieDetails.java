package com.wingoku.moviescatalogue.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_details", indices = {@Index(value = {"movieId"},
        unique = true)})
public class MovieDetails implements Parcelable {
    @PrimaryKey
    private int movieId;
    private String title;
    private String subTitle;

    public final static Creator<MovieDetails> CREATOR = new Creator<MovieDetails>() {
        @SuppressWarnings({
                "unchecked"
        })
        public MovieDetails createFromParcel(Parcel in) {
            return new MovieDetails(in);
        }

        public MovieDetails[] newArray(int size) {
            return (new MovieDetails[size]);
        }
    };

    protected MovieDetails(Parcel in) {
        this.movieId = ((int) in.readValue((int.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.subTitle = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MovieDetails() {
    }

    public MovieDetails(int movieId, String title, String subTitle) {
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