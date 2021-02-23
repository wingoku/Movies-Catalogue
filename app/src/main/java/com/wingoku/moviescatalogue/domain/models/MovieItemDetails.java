package com.wingoku.moviescatalogue.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieItemDetails implements Parcelable {
    private int movieId;
    private String movieName;
    private String movieDescription;
    private String moviePrice;
    private String imageUrl;
    private boolean isAvailable;

    public final static Parcelable.Creator<MovieItemDetails> CREATOR = new Creator<MovieItemDetails>() {
        @SuppressWarnings({
                "unchecked"
        })
        public MovieItemDetails createFromParcel(Parcel in) {
            return new MovieItemDetails(in);
        }

        public MovieItemDetails[] newArray(int size) {
            return (new MovieItemDetails[size]);
        }
    };

    protected MovieItemDetails(Parcel in) {
        this.movieId = ((int) in.readValue((int.class.getClassLoader())));
        this.movieName = ((String) in.readValue((String.class.getClassLoader())));
        this.movieDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.moviePrice = ((String) in.readValue((String.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.isAvailable = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    public MovieItemDetails() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMoviePrice() {
        return moviePrice;
    }

    public void setMoviePrice(String moviePrice) {
        this.moviePrice = moviePrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(movieId);
        dest.writeValue(movieName);
        dest.writeValue(movieDescription);
        dest.writeValue(moviePrice);
        dest.writeValue(imageUrl);
        dest.writeValue(isAvailable);
    }

    public int describeContents() {
        return 0;
    }
}
