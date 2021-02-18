package com.wingoku.moviescatalogue.data.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieOfferDTO implements Parcelable {

    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("available")
    @Expose
    private boolean available;
    @SerializedName("movie_id")
    @Expose
    private int movieId;
    public final static Parcelable.Creator<MovieOfferDTO> CREATOR = new Creator<MovieOfferDTO>() {
        @SuppressWarnings({
                "unchecked"
        })
        public MovieOfferDTO createFromParcel(Parcel in) {
            return new MovieOfferDTO(in);
        }

        public MovieOfferDTO[] newArray(int size) {
            return (new MovieOfferDTO[size]);
        }
    };

    protected MovieOfferDTO(Parcel in) {
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.available = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.movieId = ((int) in.readValue((int.class.getClassLoader())));
    }

    public MovieOfferDTO() {
    }

    public MovieOfferDTO(int movieId, String price, String image, boolean available) {
        this.movieId = movieId;
        this.price = price;
        this.image = image;
        this.available = available;
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
    }

    public int describeContents() {
        return 0;
    }
}