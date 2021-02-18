package com.wingoku.moviescatalogue.data.network.models;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesListDTO implements Parcelable {

    @SerializedName("image_base")
    @Expose
    private String imageBase;
    @SerializedName("movie_offers")
    @Expose
    private List<MovieOfferDTO> movieOffers = null;
    public final static Parcelable.Creator<MoviesListDTO> CREATOR = new Creator<MoviesListDTO>() {
        @SuppressWarnings({
                "unchecked"
        })
        public MoviesListDTO createFromParcel(Parcel in) {
            return new MoviesListDTO(in);
        }

        public MoviesListDTO[] newArray(int size) {
            return (new MoviesListDTO[size]);
        }
    };

    protected MoviesListDTO(Parcel in) {
        this.imageBase = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.movieOffers, (MovieOfferDTO.class.getClassLoader()));
    }

    public MoviesListDTO() {
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