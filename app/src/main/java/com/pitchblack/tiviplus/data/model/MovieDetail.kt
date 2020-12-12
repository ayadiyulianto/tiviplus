package com.pitchblack.tiviplus.data.model

import com.pitchblack.tiviplus.data.network.dto.*

data class MovieDetail (
    val id: Int,
    val title: String,
    val backdropPath: String,
    val posterPath: String,
    val overview: String,
    val tagline: String,
    val genres: String,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Double,
    val voteCount: Int,
    val spokenLanguages: String,
    val homepage: String,
    val revenue: Int,
    val budget: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val productionCompanies: String,
    val productionCountries: String,
    /*val contentRating: ContentRating,*/
    val credits: List<Cast>,
    val videos: List<Video>,
    val reviews: List<Review>,
    val recommendations: List<Movie>,
    val similar: List<Movie>
)