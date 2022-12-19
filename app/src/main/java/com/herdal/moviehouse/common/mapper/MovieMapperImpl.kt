package com.herdal.moviehouse.common.mapper

import com.herdal.moviehouse.data.remote.model.movies.MovieDto
import com.herdal.moviehouse.domain.uimodel.MovieUiModel

class MovieMapperImpl : MovieMapper {
    override fun toDomain(t: MovieDto): MovieUiModel = MovieUiModel(
        adult = t.adult,
        backdrop_path = t.backdrop_path,
        genre_ids = t.genre_ids,
        id = t.id,
        original_language = t.original_language,
        original_title = t.original_title,
        overview = t.overview,
        popularity = t.popularity,
        poster_path = t.poster_path,
        release_date = t.release_date,
        title = t.title,
        video = t.video,
        vote_average = t.vote_average,
        vote_count = t.vote_count
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