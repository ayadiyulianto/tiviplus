package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.Season
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonDTO(
    val id: Int,
    val name: String = "",
    val overview: String = "",
    val poster_path: String = "",
    val air_date: String = "",
    val episode_count: Int = 0,
    val season_number: Int = 0
)

fun List<SeasonDTO>.toDomainModel(): List<Season> {
    return this.map {
        Season(
            id = it.id,
            name = it.name,
            overview = it.overview,
            posterPath = it.poster_path,
            airDate = it.air_date,
            episodeCount = it.episode_count
        )
    }
}