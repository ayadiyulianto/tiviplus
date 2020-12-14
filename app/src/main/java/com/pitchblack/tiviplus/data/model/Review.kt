package com.pitchblack.tiviplus.data.model

data class Review(
    val id: String,
    val createdAt: String,
    val author: String,
    val rating: Int,
    val content: String,
    val url: String
)
