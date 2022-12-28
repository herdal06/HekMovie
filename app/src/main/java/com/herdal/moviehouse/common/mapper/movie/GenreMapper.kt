package com.herdal.moviehouse.common.mapper.movie

import com.herdal.moviehouse.common.mapper.DtoMapper
import com.herdal.moviehouse.data.remote.model.genre.GenreDto
import com.herdal.moviehouse.domain.uimodel.GenreUiModel

class GenreMapper : DtoMapper<GenreDto, GenreUiModel> {
    override fun toDomain(response: GenreDto): GenreUiModel = GenreUiModel(
        id = response.id,
        name = response.name
    )

    override fun fromDomain(domainModel: GenreUiModel): GenreDto = GenreDto(
        id = domainModel.id,
        name = domainModel.name
    )

    override fun toDomainList(tList: List<GenreDto>): List<GenreUiModel> =
        tList.map { response ->
            toDomain(response)
        }

    override fun fromDomainList(domainList: List<GenreUiModel>): List<GenreDto> =
        domainList.map { uimodel ->
            fromDomain(uimodel)
        }
}