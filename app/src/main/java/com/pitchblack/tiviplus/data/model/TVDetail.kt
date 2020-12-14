package com.pitchblack.tiviplus.data.model

import com.pitchblack.tiviplus.data.network.dto.*

data class TVDetail (
    val id: Int,
    val name: String,
    val createdBy: String,
    val backdropPath: String,
    val posterPath: String,
    val overview: String,
    val tagline: String,
    val genres: String,
    val firstAirDate: String,
    val lastAirDate: String,
    val networks: String,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val episodeRunTime: Int,
    val voteAverage: Double,
    val voteCount: Int,
    val languages: String,
    val homepage: String,
    val originalName: String,
    val productionCompanies: String,
    val originCountry: String,
    val seasons: List<Season>
)