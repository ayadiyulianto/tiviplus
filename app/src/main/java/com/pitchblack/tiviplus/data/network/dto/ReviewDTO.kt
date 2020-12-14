package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.Review
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewDTO(
    val id: String,
    val created_at: String = "",
    val author: String = "",
    val author_details: AuthorDTO,
    val content: String = "",
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class AuthorDTO(val rating: Int = 0)

@JsonClass(generateAdapter = true)
data class ReviewListDTO(
    val results: List<ReviewDTO>
)

fun ReviewListDTO.toDomainModel(): List<Review> {
    return results.map {
        Review(
            id = it.id,
            createdAt = it.created_at,
            author = it.author,
            rating = it.author_details.rating,
            content = it.content,
            url = it.url
        )
    }
}