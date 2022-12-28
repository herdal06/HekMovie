package com.herdal.moviehouse.common.mapper.review

import com.herdal.moviehouse.common.mapper.DtoMapper
import com.herdal.moviehouse.data.remote.model.review.AuthorDetailsDto
import com.herdal.moviehouse.domain.uimodel.AuthorDetailsUiModel

class AuthorMapper : DtoMapper<AuthorDetailsDto, AuthorDetailsUiModel> {
    override fun toDomain(response: AuthorDetailsDto): AuthorDetailsUiModel = AuthorDetailsUiModel(
        avatar_path = response.avatar_path,
        name = response.name,
        rating = response.rating,
        username = response.username
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