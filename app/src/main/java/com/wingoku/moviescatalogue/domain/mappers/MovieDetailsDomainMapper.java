package com.wingoku.moviescatalogue.domain.mappers;

import com.wingoku.moviescatalogue.data.network.models.MovieDetailsDTO;
import com.wingoku.moviescatalogue.domain.mappers.interfaces.DomainMapper;
import com.wingoku.moviescatalogue.domain.models.MovieDetails;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsDomainMapper implements DomainMapper<MovieDetails, MovieDetailsDTO> {
    @Override
    public MovieDetails fromDTO(MovieDetailsDTO movieDetailsDTO) {
        return new MovieDetails(
                movieDetailsDTO.getMovieId(),
                movieDetailsDTO.getTitle(),
                movieDetailsDTO.getSubTitle()
        );
    }

    @Override
    public MovieDetailsDTO toDTO(MovieDetails movieDetails) {
        return new MovieDetailsDTO(
                movieDetails.getMovieId(),
                movieDetails.getTitle(),
                movieDetails.getSubTitle()
        );
    }

    @Override
    public List<MovieDetails> fromDTO(List<MovieDetailsDTO> movieDetailsDTOS) {
        List<MovieDetails> list = new ArrayList<>();

        for (MovieDetailsDTO dto : movieDetailsDTOS) {
            list.add(fromDTO(dto));
        }
        return list;
    }

    @Override
    public List<MovieDetailsDTO> toDTO(List<MovieDetails> movieDetails) {
        List<MovieDetailsDTO> list = new ArrayList<>();

        for (MovieDetails dto : movieDetails) {
            list.add(toDTO(dto));
        }
        return list;
    }
}
