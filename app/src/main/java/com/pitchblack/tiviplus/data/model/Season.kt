package com.pitchblack.tiviplus.data.model

data class Season(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val airDate: String,
    val episodeCount: Int
)