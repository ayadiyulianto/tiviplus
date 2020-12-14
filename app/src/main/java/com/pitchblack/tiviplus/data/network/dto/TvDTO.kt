package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.TV
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvDTO (
    val id: Int,
    val popularity: Double = 0.0,
    val overview: String = "",
    val poster_path: String? = "",
    val first_air_date: String = "",
    val name: String = "",
    val vote_average: Double = 0.0
) {
    val posterPath = poster_path ?: ""
}

@JsonClass(generateAdapter = true)
data class TvListDTO (
    val results: List<TvDTO>
)

fun TvListDTO.toDomainModel(): List<TV> {
    return results.map {
        TV(
            id = it.id,
            overview = it.overview,
            posterPath = it.posterPath,
            firstAirDate = it.first_air_date,
            name = it.name,
            voteAverage = it.vote_average
        )
    }
}