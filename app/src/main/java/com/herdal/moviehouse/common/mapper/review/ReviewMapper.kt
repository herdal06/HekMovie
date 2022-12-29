package com.herdal.moviehouse.common.mapper.review

import com.herdal.moviehouse.common.mapper.DtoMapper
import com.herdal.moviehouse.data.remote.model.review.ReviewDto
import com.herdal.moviehouse.domain.uimodel.review.ReviewUiModel

class ReviewMapper(
    private val authorMapper: AuthorMapper
) : DtoMapper<ReviewDto, ReviewUiModel> {
    override fun toDomain(response: ReviewDto): ReviewUiModel {

        val authorDetails = response.author_details.let { authorMapper.toDomain(it) }

        return ReviewUiModel(
            author = response.author,
            author_details = authorDetails,
            content = response.content,
            created_at = response.created_at,
            id = response.id,
            updated_at = response.updated_at,
            url = response.url
        )
    }

    override fun fromDomain(domainModel: ReviewUiModel): ReviewDto {

        val authorDetails = domainModel.author_details.let { authorMapper.fromDomain(it) }

        return ReviewDto(
            author = domainModel.author,
            author_details = authorDetails,
            content = domainModel.content,
            created_at = domainModel.created_at,
            id = domainModel.id,
            updated_at = domainModel.updated_at,
            url = domainModel.url
        )
    }

    override fun toDomainList(tList: List<ReviewDto>): List<ReviewUiModel> =
        tList.map { reviewDto ->
            toDomain(reviewDto)
        }

    override fun fromDomainList(domainList: List<ReviewUiModel>): List<ReviewDto> =
        domainList.map { reviewUiModel ->
            fromDomain(reviewUiModel)
        }
}