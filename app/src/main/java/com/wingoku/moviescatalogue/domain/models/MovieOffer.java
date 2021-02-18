package com.wingoku.moviescatalogue.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_offer", indices = {@Index(value = {"movieId"},
        unique = true)})
public class MovieOffer implements Parcelable {
    @PrimaryKey
    private int movieId;
    private String price;
    private String image;
    private boolean available;
    private String imageBase;

    public final static Creator<MovieOffer> CREATOR = new Creator<MovieOffer>() {
        @SuppressWarnings({
                "unchecked"
        })
        public MovieOffer createFromParcel(Parcel in) {
            return new MovieOffer(in);
        }

        public MovieOffer[] newArray(int size) {
            return (new MovieOffer[size]);
        }
    };

    protected MovieOffer(Parcel in) {
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.imageBase = ((String) in.readValue((String.class.getClassLoader())));
        this.available = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.movieId = ((int) in.readValue((int.class.getClassLoader())));
    }

    public MovieOffer() {
    }

    public MovieOffer(int movieId, String price, String image, boolean available, String imageBase) {
        this.movieId = movieId;
        this.price = price;
        this.image = image;
        this.available = available;
        this.imageBase = imageBase;
    }

    public String getImageBase() {
        return imageBase;
    }

    public void setImageBase(String imageBase) {
        this.imageBase = imageBase;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(price);
        dest.writeValue(image);
        dest.writeValue(available);
        dest.writeValue(movieId);
        dest.writeValue(imageBase);
    }

    public int describeContents() {
        return 0;
    }
}