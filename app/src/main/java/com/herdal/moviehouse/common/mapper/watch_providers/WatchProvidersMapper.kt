package com.herdal.moviehouse.common.mapper.watch_providers

import com.herdal.moviehouse.common.mapper.BaseMapper
import com.herdal.moviehouse.data.remote.model.watch_provider.WatchProviderDto
import com.herdal.moviehouse.domain.uimodel.watch_providers.WatchProviderUiModel

class WatchProvidersMapper(
    private val usMapper: USMapper
) : BaseMapper<WatchProviderDto, WatchProviderUiModel> {
    override fun toDomain(response: WatchProviderDto): WatchProviderUiModel {
        val us = usMapper.toDomain(response.US)
        return WatchProviderUiModel(
            US = us
        )
    }

    override fun fromDomain(domainModel: WatchProviderUiModel): WatchProviderDto {
        val us = usMapper.fromDomain(domainModel.US)
        return WatchProviderDto(
            US = us
        )
    }

    override fun toDomainList(tList: List<WatchProviderDto>): List<WatchProviderUiModel> =
        tList.map { watchProviderDto ->
            toDomain(watchProviderDto)
        }

    override fun fromDomainList(domainList: List<WatchProviderUiModel>): List<WatchProviderDto> =
        domainList.map { watchProviderUiModel ->
            fromDomain(watchProviderUiModel)
        }
}