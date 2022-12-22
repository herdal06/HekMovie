package com.herdal.moviehouse.common.mapper

import com.herdal.moviehouse.data.remote.model.review.AuthorDetailsDto
import com.herdal.moviehouse.domain.uimodel.AuthorDetailsUiModel

class AuthorMapperImpl : AuthorMapper {
    override fun toDomain(t: AuthorDetailsDto): AuthorDetailsUiModel = AuthorDetailsUiModel(
        avatar_path = t.avatar_path,
        name = t.name,
        rating = t.rating,
        username = t.username
    )

    override fun fromDomain(domainModel: AuthorDetailsUiModel): AuthorDetailsDto = AuthorDetailsDto(
        avatar_path = domainModel.avatar_path,
        name = domainModel.name,
        rating = domainModel.rating,
        username = domainModel.username
    )

    override fun toDomainList(tList: List<AuthorDetailsDto>): List<AuthorDetailsUiModel> =
        tList.map { authorDto ->
            toDomain(authorDto)
        }

    override fun fromDomainList(domainList: List<AuthorDetailsUiModel>): List<AuthorDetailsDto> =
        domainList.map { authorUiModel ->
            fromDomain(authorUiModel)
        }
}