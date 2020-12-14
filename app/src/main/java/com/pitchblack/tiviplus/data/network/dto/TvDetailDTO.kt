package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.TVDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvDetailDTO(
    val id: Int,
    val name: String = "",
    val created_by: List<CreatorDTO>,
    val backdrop_path: String = "",
    val poster_path: String = "",
    val overview: String = "",
    val tagline: String = "",
    val genres: List<GenreDTO>,
    val first_air_date: String = "",
    val last_air_date: String = "",
    val networks: List<NetworkDTO>,
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val episode_run_time: List<Int>,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val languages: List<String>,
    val homepage: String = "",
    val revenue: Int = 0,
    val budget: Int = 0,
    val original_language: String = "",
    val original_name: String = "",
    val production_companies: List<CompanyDTO>,
    val origin_country: List<String>,
    val seasons: List<SeasonDTO>
)

@JsonClass(generateAdapter = true)
data class CreatorDTO(val name: String = "")

@JsonClass(generateAdapter = true)
data class NetworkDTO(val name: String = "")

fun TvDetailDTO.toDomainModel(): TVDetail {
    return TVDetail(
        id = id,
        name = name,
        createdBy = created_by.concatCreatorToString(),
        backdropPath = backdrop_path,
        posterPath = poster_path,
        overview = overview,
        tagline = tagline,
        genres = genres.concatGenreToString(),
        firstAirDate = first_air_date,
        lastAirDate = last_air_date,
        networks = networks.concatNetworksToString(),
        numberOfEpisodes = number_of_episodes,
        numberOfSeasons = number_of_seasons,
        episodeRunTime = episode_run_time[0],
        voteAverage = vote_average,
        voteCount = vote_count,
        languages = languages.joinToString(", "),
        homepage = homepage,
        originalName = original_name,
        productionCompanies = production_companies.concatCompanyToString(),
        originCountry = origin_country.joinToString(", "),
        seasons = seasons.toDomainModel()
    )
}

fun List<CreatorDTO>.concatCreatorToString(): String {
    return this.joinToString(", ") {
        it.name
    }
}

fun List<NetworkDTO>.concatNetworksToString(): String {
    return this.joinToString(", ") {
        it.name
    }
}