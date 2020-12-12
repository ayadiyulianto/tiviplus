package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.Movie
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDTO (
    val id: Int,
    val popularity: Double = 0.0,
    val overview: String = "",
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val vote_average: Double = 0.0
)

@JsonClass(generateAdapter = true)
data class MovieListDTO (
    val results: List<MovieDTO>
)

fun MovieListDTO.toDomainModel(): List<Movie> {
    return results.map {
        Movie(
            id = it.id,
            overview = it.overview,
            posterPath = it.poster_path,
            releaseDate = it.release_date,
            title = it.title,
            voteAverage = it.vote_average
        )
    }
}