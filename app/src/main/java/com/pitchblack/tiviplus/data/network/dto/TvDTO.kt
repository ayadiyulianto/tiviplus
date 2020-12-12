package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.Movie
import com.pitchblack.tiviplus.data.model.TV

data class TvDTO (
    val id: Int,
    val popularity: Double,
    val overview: String,
    val poster_path: String,
    val first_air_date: String,
    val name: String,
    val vote_average: Double
)

data class TvListDTO (
    val results: List<TvDTO>
)

fun TvListDTO.toDomainModel(): List<TV> {
    return results.map {
        TV(
            id = it.id,
            overview = it.overview,
            posterPath = it.poster_path,
            firstAirDate = it.first_air_date,
            name = it.name,
            voteAverage = it.vote_average
        )
    }
}