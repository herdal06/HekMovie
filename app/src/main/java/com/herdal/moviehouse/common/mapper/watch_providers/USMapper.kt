package com.herdal.moviehouse.common.mapper.watch_providers

import com.herdal.moviehouse.common.mapper.BaseMapper
import com.herdal.moviehouse.data.remote.model.watch_provider.USDto
import com.herdal.moviehouse.domain.uimodel.watch_providers.USUiModel

class USMapper(
    private val buyMapper: BuyMapper
) : BaseMapper<USDto, USUiModel> {
    override fun toDomain(response: USDto): USUiModel {
        val buyList = buyMapper.toDomainList(response.buy)
        return USUiModel(
            buy = buyList
        )
    }

    override fun fromDomain(domainModel: USUiModel): USDto {
        val buyList = buyMapper.fromDomainList(domainModel.buy)
        return USDto(
            buy = buyList
        )
    }

    override fun toDomainList(tList: List<USDto>): List<USUiModel> =
        tList.map { USDto ->
            toDomain(USDto)
        }

    override fun fromDomainList(domainList: List<USUiModel>): List<USDto> =
        domainList.map { USUiModel ->
            fromDomain(USUiModel)
        }
}