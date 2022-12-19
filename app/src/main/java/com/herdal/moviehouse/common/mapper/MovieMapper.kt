package com.herdal.moviehouse.common.mapper

import com.herdal.moviehouse.data.remote.model.movies.MovieDto
import com.herdal.moviehouse.domain.uimodel.MovieUiModel

interface MovieMapper {
    fun toDomain(t: MovieDto): MovieUiModel
    fun fromDomain(domainModel: MovieUiModel): MovieDto
    fun toDomainList(tList: List<MovieDto>): List<MovieUiModel>
    fun fromDomainList(domainList: List<MovieUiModel>): List<MovieDto>
}