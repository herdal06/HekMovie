package com.herdal.moviehouse.common.mapper

import com.herdal.moviehouse.data.remote.model.review.ReviewDto
import com.herdal.moviehouse.domain.uimodel.ReviewUiModel

interface ReviewMapper {

    fun toDomain(t: ReviewDto): ReviewUiModel
    fun fromDomain(domainModel: ReviewUiModel): ReviewDto
    fun toDomainList(tList: List<ReviewDto>): List<ReviewUiModel>
    fun fromDomainList(domainList: List<ReviewUiModel>): List<ReviewDto>
}