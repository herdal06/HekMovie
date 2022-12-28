package com.herdal.moviehouse.common.mapper.movie

import com.herdal.moviehouse.common.mapper.DtoMapper
import com.herdal.moviehouse.data.remote.model.movies.MovieDto
import com.herdal.moviehouse.domain.uimodel.MovieUiModel

class MovieMapper : DtoMapper<MovieDto, MovieUiModel> {
    override fun toDomain(response: MovieDto): MovieUiModel = MovieUiModel(
        adult = response.adult,
        backdrop_path = response.backdrop_path,
        genre_ids = response.genre_ids,
        id = response.id,
        original_language = response.original_language,
        original_title = response.original_title,
        overview = response.overview,
        popularity = response.popularity,
        poster_path = response.poster_path,
        release_date = response.release_date,
        title = response.title,
        video = response.video,
        vote_average = response.vote_average,
        vote_count = response.vote_count
    )

    override fun fromDomain(domainModel: MovieUiModel): MovieDto = MovieDto(
        adult = domainModel.adult,
        backdrop_path = domainModel.backdrop_path,
        genre_ids = domainModel.genre_ids,
        id = domainModel.id,
        original_language = domainModel.original_language,
        original_title = domainModel.original_title,
        overview = domainModel.overview,
        popularity = domainModel.popularity,
        poster_path = domainModel.poster_path,
        release_date = domainModel.release_date,
        title = domainModel.title,
        video = domainModel.video,
        vote_average = domainModel.vote_average,
        vote_count = domainModel.vote_count
    )

    override fun toDomainList(tList: List<MovieDto>): List<MovieUiModel> =
        tList.map { response ->
            toDomain(response)
        }

    override fun fromDomainList(domainList: List<MovieUiModel>): List<MovieDto> =
        domainList.map { uiModel ->
            fromDomain(uiModel)
        }
}