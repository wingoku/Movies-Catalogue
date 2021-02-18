package com.wingoku.moviescatalogue.data.network.models;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesDetailsDTO implements Parcelable {

    @SerializedName("movie_data")
    @Expose
    private List<DetailsDTO> movieData = null;
    public final static Parcelable.Creator<MoviesDetailsDTO> CREATOR = new Creator<MoviesDetailsDTO>() {
        @SuppressWarnings({
                "unchecked"
        })
        public MoviesDetailsDTO createFromParcel(Parcel in) {
            return new MoviesDetailsDTO(in);
        }

        public MoviesDetailsDTO[] newArray(int size) {
            return (new MoviesDetailsDTO[size]);
        }
    };

    protected MoviesDetailsDTO(Parcel in) {
        in.readList(this.movieData, (DetailsDTO.class.getClassLoader()));
    }

    public MoviesDetailsDTO() {
    }

    public List<DetailsDTO> getMovieData() {
        return movieData;
    }

    public void setMovieData(List<DetailsDTO> movieData) {
        this.movieData = movieData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(movieData);
    }

    public int describeContents() {
        return 0;
    }

}