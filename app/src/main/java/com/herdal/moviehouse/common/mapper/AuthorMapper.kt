package com.herdal.moviehouse.common.mapper

import com.herdal.moviehouse.data.remote.model.review.AuthorDetailsDto
import com.herdal.moviehouse.domain.uimodel.AuthorDetailsUiModel

interface AuthorMapper {

    fun toDomain(t: AuthorDetailsDto): AuthorDetailsUiModel
    fun fromDomain(domainModel: AuthorDetailsUiModel): AuthorDetailsDto
    fun toDomainList(tList: List<AuthorDetailsDto>): List<AuthorDetailsUiModel>
    fun fromDomainList(domainList: List<AuthorDetailsUiModel>): List<AuthorDetailsDto>
}