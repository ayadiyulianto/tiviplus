package com.pitchblack.tiviplus.data.model

data class TVDetail (
    val id: Int,
    val overview: String,
    val posterPath: String,
    val firstAirDate: String,
    val name: String,
    val voteAverage: Double,
    val voteCount: Int
)