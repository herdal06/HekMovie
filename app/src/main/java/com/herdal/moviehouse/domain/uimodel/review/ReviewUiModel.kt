package com.herdal.moviehouse.domain.uimodel.review

data class ReviewUiModel(
    val author: String,
    val author_details: AuthorDetailsUiModel,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)