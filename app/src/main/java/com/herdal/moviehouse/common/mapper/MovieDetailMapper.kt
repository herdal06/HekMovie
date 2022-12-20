package com.herdal.moviehouse.common.mapper

import com.herdal.moviehouse.data.remote.model.movie_detail.MovieDetailDto
import com.herdal.moviehouse.domain.uimodel.MovieDetailUiModel

interface MovieDetailMapper {
    fun toDomain(t: MovieDetailDto): MovieDetailUiModel
    fun fromDomain(domainModel: MovieDetailUiModel): MovieDetailDto
    fun toDomainList(tList: List<MovieDetailDto>): List<MovieDetailUiModel>
    fun fromDomainList(domainList: List<MovieDetailUiModel>): List<MovieDetailDto>
}