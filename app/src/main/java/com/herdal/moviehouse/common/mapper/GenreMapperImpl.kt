package com.herdal.moviehouse.common.mapper

import com.herdal.moviehouse.data.remote.model.genre.GenreDto
import com.herdal.moviehouse.domain.uimodel.GenreUiModel

class GenreMapperImpl : GenreMapper {
    override fun toDomain(t: GenreDto): GenreUiModel = GenreUiModel(
        id = t.id,
        name = t.name
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