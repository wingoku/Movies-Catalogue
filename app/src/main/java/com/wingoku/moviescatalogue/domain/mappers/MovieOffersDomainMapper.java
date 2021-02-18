package com.wingoku.moviescatalogue.domain.mappers;

import com.wingoku.moviescatalogue.data.network.models.MovieOfferDTO;
import com.wingoku.moviescatalogue.domain.mappers.interfaces.DomainMapper;
import com.wingoku.moviescatalogue.domain.models.MovieOffer;

import java.util.ArrayList;
import java.util.List;

public class MovieOffersDomainMapper implements DomainMapper<MovieOffer, MovieOfferDTO> {
    private String baseUrl;

    @Override
    public MovieOffer fromDTO(MovieOfferDTO movieOfferDTO) {
        return new MovieOffer(
                movieOfferDTO.getMovieId(),
                movieOfferDTO.getPrice(),
                movieOfferDTO.getImage(),
                movieOfferDTO.isAvailable(),
                getBaseUrl()
        );
    }

    @Override
    public MovieOfferDTO toDTO(MovieOffer movieOffer) {
        return new MovieOfferDTO(
                movieOffer.getMovieId(),
                movieOffer.getPrice(),
                movieOffer.getImage(),
                movieOffer.isAvailable()
        );
    }

    @Override
    public List<MovieOffer> fromDTO(List<MovieOfferDTO> movieOfferDTOS) {
        List<MovieOffer> list = new ArrayList<>();

        for (MovieOfferDTO dto : movieOfferDTOS) {
            list.add(fromDTO(dto));
        }
        return list;
    }

    @Override
    public List<MovieOfferDTO> toDTO(List<MovieOffer> movieOffers) {
        List<MovieOfferDTO> list = new ArrayList<>();

        for (MovieOffer dto : movieOffers) {
            list.add(toDTO(dto));
        }
        return list;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
