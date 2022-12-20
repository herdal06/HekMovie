package com.herdal.moviehouse.common.mapper

import com.herdal.moviehouse.data.remote.model.movie_detail.CompanyDto
import com.herdal.moviehouse.domain.uimodel.CompanyUiModel

interface CompanyMapper {
    fun toDomain(t: CompanyDto): CompanyUiModel
    fun fromDomain(domainModel: CompanyUiModel): CompanyDto
    fun toDomainList(tList: List<CompanyDto>): List<CompanyUiModel>
    fun fromDomainList(domainList: List<CompanyUiModel>): List<CompanyDto>
}