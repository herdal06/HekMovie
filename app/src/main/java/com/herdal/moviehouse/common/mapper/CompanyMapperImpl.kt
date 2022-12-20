package com.herdal.moviehouse.common.mapper

import com.herdal.moviehouse.data.remote.model.movie_detail.CompanyDto
import com.herdal.moviehouse.domain.uimodel.CompanyUiModel

class CompanyMapperImpl : CompanyMapper {
    override fun toDomain(t: CompanyDto): CompanyUiModel = CompanyUiModel(
        id = t.id,
        logo_path = t.logo_path,
        name = t.name,
        origin_country = t.origin_country
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