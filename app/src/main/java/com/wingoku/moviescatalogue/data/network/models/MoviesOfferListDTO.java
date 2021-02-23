package com.wingoku.moviescatalogue.data.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesOfferListDTO implements Parcelable {

    @SerializedName("image_base")
    @Expose
    private String imageBase;
    @SerializedName("movie_offers")
    @Expose
    private List<MovieOfferDTO> movieOffers = null;
    public final static Parcelable.Creator<MoviesOfferListDTO> CREATOR = new Creator<MoviesOfferListDTO>() {
        @SuppressWarnings({
                "unchecked"
        })
        public MoviesOfferListDTO createFromParcel(Parcel in) {
            return new MoviesOfferListDTO(in);
        }

        public MoviesOfferListDTO[] newArray(int size) {
            return (new MoviesOfferListDTO[size]);
        }
    };

    protected MoviesOfferListDTO(Parcel in) {
        this.imageBase = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.movieOffers, (MovieOfferDTO.class.getClassLoader()));
    }

    public MoviesOfferListDTO() {
    }

    public String getImageBase() {
        return imageBase;
    }

    public void setImageBase(String imageBase) {
        this.imageBase = imageBase;
    }

    public List<MovieOfferDTO> getMovieOffers() {
        return movieOffers;
    }

    public void setMovieOffers(List<MovieOfferDTO> movieOffers) {
        this.movieOffers = movieOffers;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(imageBase);
        dest.writeList(movieOffers);
    }

    public int describeContents() {
        return 0;
    }
}