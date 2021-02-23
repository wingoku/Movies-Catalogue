package com.wingoku.moviescatalogue.data.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesDetailsListDTO implements Parcelable {

    @SerializedName("movie_data")
    @Expose
    private List<MovieDetailsDTO> movieData = null;
    public final static Parcelable.Creator<MoviesDetailsListDTO> CREATOR = new Creator<MoviesDetailsListDTO>() {
        @SuppressWarnings({
                "unchecked"
        })
        public MoviesDetailsListDTO createFromParcel(Parcel in) {
            return new MoviesDetailsListDTO(in);
        }

        public MoviesDetailsListDTO[] newArray(int size) {
            return (new MoviesDetailsListDTO[size]);
        }
    };

    protected MoviesDetailsListDTO(Parcel in) {
        in.readList(this.movieData, (MovieDetailsDTO.class.getClassLoader()));
    }

    public MoviesDetailsListDTO() {
    }

    public List<MovieDetailsDTO> getMovieData() {
        return movieData;
    }

    public void setMovieData(List<MovieDetailsDTO> movieData) {
        this.movieData = movieData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(movieData);
    }

    public int describeContents() {
        return 0;
    }

}