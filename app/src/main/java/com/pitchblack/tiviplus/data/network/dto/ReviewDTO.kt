package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.Review
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewDTO(
    val id: Int,
    val author: String = "",
    val content: String = "",
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class ReviewsDTO(
    val results: List<ReviewDTO>
)

fun ReviewsDTO.toDomainModel(): List<Review> {
    return results.map {
        Review(
            id = it.id,
            author = it.author,
            content = it.content,
            url = it.url
        )
    }
}