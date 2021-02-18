package com.wingoku.moviescatalogue.domain.mappers;

import com.wingoku.moviescatalogue.data.network.models.DetailsDTO;
import com.wingoku.moviescatalogue.data.network.models.MoviesDetailsDTO;
import com.wingoku.moviescatalogue.domain.mappers.interfaces.DomainMapper;
import com.wingoku.moviescatalogue.domain.models.MovieDetails;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsDomainMapper implements DomainMapper<MovieDetails, DetailsDTO> {
    @Override
    public MovieDetails fromDTO(DetailsDTO detailsDTO) {
        return new MovieDetails(
                detailsDTO.getMovieId(),
                detailsDTO.getTitle(),
                detailsDTO.getSubTitle()
        );
    }

    @Override
    public DetailsDTO toDTO(MovieDetails movieDetails) {
        return new DetailsDTO(
                movieDetails.getMovieId(),
                movieDetails.getTitle(),
                movieDetails.getSubTitle()
        );
    }

    @Override
    public List<MovieDetails> fromDTO(List<DetailsDTO> detailsDTOS) {
        List<MovieDetails> list = new ArrayList<>();

        for (DetailsDTO dto : detailsDTOS) {
            list.add(fromDTO(dto));
        }
        return list;
    }

    @Override
    public List<DetailsDTO> toDTO(List<MovieDetails> movieDetails) {
        List<DetailsDTO> list = new ArrayList<>();

        for (MovieDetails dto : movieDetails) {
            list.add(toDTO(dto));
        }
        return list;
    }
}
