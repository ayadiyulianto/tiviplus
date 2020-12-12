package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.MovieDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailDTO(
    val id: Int,
    val title: String = "",
    val backdrop_path: String = "",
    val poster_path: String = "",
    val overview: String = "",
    val tagline: String = "",
    val genres: List<GenreDTO>,
    val release_date: String = "",
    val runtime: Int = 0,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val spoken_languages: List<LanguageDTO>,
    val homepage: String = "",
    val revenue: Int = 0,
    val budget: Int = 0,
    val original_language: String = "",
    val original_title: String = "",
    val production_companies: List<CompanyDTO>,
    val production_countries: List<CountryDTO>,
    /*val release_dates: ContentRating,*/
    val credits: CreditsDTO,
    val videos: VideosDTO,
    val reviews: ReviewsDTO,
    val recommendations: MovieListDTO,
    val similar: MovieListDTO
)

@JsonClass(generateAdapter = true)
data class GenreDTO(val id: Int, val name: String = "")

@JsonClass(generateAdapter = true)
data class LanguageDTO(val name: String = "")

@JsonClass(generateAdapter = true)
data class CompanyDTO(val name: String = "")

@JsonClass(generateAdapter = true)
data class CountryDTO(val name: String = "")

fun MovieDetailDTO.toDomainModel(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        backdropPath = backdrop_path,
        posterPath = poster_path,
        overview = overview,
        tagline = tagline,
        genres = genres.concat0ToString(),
        releaseDate = release_date,
        runtime = runtime,
        voteAverage = vote_average,
        voteCount = vote_count,
        spokenLanguages = spoken_languages.concatToString(),
        revenue = revenue,
        homepage = homepage,
        budget = budget,
        originalLanguage = original_language,
        originalTitle = original_title,
        productionCompanies = production_companies.concat2ToString(),
        productionCountries = production_countries.concat3ToString(),
        /*contentRating: ContentRating,*/
        credits = credits.toDomainModel(),
        videos = videos.toDomainModel(),
        reviews = reviews.toDomainModel(),
        recommendations = recommendations.toDomainModel(),
        similar = similar.toDomainModel()
    )
}

fun List<GenreDTO>.concat0ToString(): String {
    return this.joinToString(", ") {
        it.name
    }
}

fun List<LanguageDTO>.concatToString(): String {
    return this.joinToString(", ") {
        it.name
    }
}

fun List<CompanyDTO>.concat2ToString(): String {
    return this.joinToString(", ") {
        it.name
    }
}

fun List<CountryDTO>.concat3ToString(): String {
    return this.joinToString(", ") {
        it.name
    }
}