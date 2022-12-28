package com.herdal.moviehouse.common.mapper.movie

import com.herdal.moviehouse.common.mapper.DtoMapper
import com.herdal.moviehouse.data.remote.model.movie_detail.CompanyDto
import com.herdal.moviehouse.domain.uimodel.CompanyUiModel

class CompanyMapper : DtoMapper<CompanyDto, CompanyUiModel> {
    override fun toDomain(response: CompanyDto): CompanyUiModel = CompanyUiModel(
        id = response.id,
        logo_path = response.logo_path,
        name = response.name,
        origin_country = response.origin_country
    )

    override fun fromDomain(domainModel: CompanyUiModel): CompanyDto = CompanyDto(
        id = domainModel.id,
        logo_path = domainModel.logo_path,
        name = domainModel.name,
        origin_country = domainModel.origin_country
    )

    override fun toDomainList(tList: List<CompanyDto>): List<CompanyUiModel> =
        tList.map { companyDto ->
            toDomain(companyDto)
        }

    override fun fromDomainList(domainList: List<CompanyUiModel>): List<CompanyDto> =
        domainList.map { companyUiModel ->
            fromDomain(companyUiModel)
        }
}