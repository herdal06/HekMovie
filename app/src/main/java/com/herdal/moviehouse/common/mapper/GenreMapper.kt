package com.herdal.moviehouse.common.mapper

import com.herdal.moviehouse.data.remote.model.genre.GenreDto
import com.herdal.moviehouse.domain.uimodel.GenreUiModel

interface GenreMapper {
    fun toDomain(t: GenreDto): GenreUiModel
    fun fromDomain(domainModel: GenreUiModel): GenreDto
    fun toDomainList(tList: List<GenreDto>): List<GenreUiModel>
    fun fromDomainList(domainList: List<GenreUiModel>): List<GenreDto>
}