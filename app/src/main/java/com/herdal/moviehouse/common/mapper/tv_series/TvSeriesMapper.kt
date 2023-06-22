package com.herdal.moviehouse.common.mapper.tv_series

import com.herdal.moviehouse.common.mapper.DtoMapper
import com.herdal.moviehouse.data.remote.model.tv_series.TvSeriesDto
import com.herdal.moviehouse.domain.uimodel.tv_series.TvSeriesUiModel

class TvSeriesMapper : DtoMapper<TvSeriesDto, TvSeriesUiModel> {
    override fun toDomain(response: TvSeriesDto): TvSeriesUiModel =
        TvSeriesUiModel(
            id = response.id,
            name = response.name,
            posterPath = response.posterPath,
            firstAirDate = response.firstAirDate,
            voteAverage = response.voteAverage.toString()
        )

    override fun fromDomain(domainModel: TvSeriesUiModel): TvSeriesDto {
        TODO("Not yet implemented")
    }

    override fun toDomainList(tList: List<TvSeriesDto>): List<TvSeriesUiModel> {
        TODO("Not yet implemented")
    }

    override fun fromDomainList(domainList: List<TvSeriesUiModel>): List<TvSeriesDto> {
        TODO("Not yet implemented")
    }
}