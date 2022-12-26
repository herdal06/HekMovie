package com.herdal.moviehouse.common.mapper.watch_providers

import com.herdal.moviehouse.common.mapper.BaseMapper
import com.herdal.moviehouse.data.remote.model.watch_provider.BuyDto
import com.herdal.moviehouse.domain.uimodel.watch_providers.BuyUiModel

class BuyMapper : BaseMapper<BuyDto, BuyUiModel> {
    override fun toDomain(response: BuyDto): BuyUiModel = BuyUiModel(
        logo_path = response.logo_path,
        provider_id = response.provider_id,
        provider_name = response.provider_name
    )

    override fun fromDomain(domainModel: BuyUiModel): BuyDto = BuyDto(
        logo_path = domainModel.logo_path,
        provider_id = domainModel.provider_id,
        provider_name = domainModel.provider_name
    )

    override fun toDomainList(tList: List<BuyDto>): List<BuyUiModel> =
        tList.map { buyDto ->
            toDomain(buyDto)
        }

    override fun fromDomainList(domainList: List<BuyUiModel>): List<BuyDto> =
        domainList.map { buyUiModel ->
            fromDomain(buyUiModel)
        }
}