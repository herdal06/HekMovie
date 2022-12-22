package com.herdal.moviehouse.common.mapper

import com.herdal.moviehouse.data.remote.model.review.ReviewDto
import com.herdal.moviehouse.domain.uimodel.ReviewUiModel

class ReviewMapperImpl(
    private val authorMapper: AuthorMapper
) : ReviewMapper {
    override fun toDomain(t: ReviewDto): ReviewUiModel {

        val authorDetails = authorMapper.toDomain(t.author_details)

        return ReviewUiModel(
            author = t.author,
            author_details = authorDetails,
            content = t.content,
            created_at = t.created_at,
            id = t.id,
            updated_at = t.updated_at,
            url = t.url
        )
    }

    override fun fromDomain(domainModel: ReviewUiModel): ReviewDto {

        val authorDetails = authorMapper.fromDomain(domainModel.author_details)

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