package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.Filmography
import com.pitchblack.tiviplus.utils.DataUtils.getYear
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmographyDTO (
    val id: Int,
    val media_type: String = "",
    val poster_path: String? = "",
    val name: String = "",
    val first_air_date: String = "",
    val title: String = "",
    val release_date: String = "",
    val character: String = ""
) {
    val posterPath = poster_path ?: ""
    val titleName: String = if (media_type == "movie") title else name
    val releaseYear: String =
        if (media_type == "movie") release_date.getYear() else first_air_date.getYear()
}

@JsonClass(generateAdapter = true)
data class FilmographyListDTO(
    val cast: List<FilmographyDTO>
)

fun FilmographyListDTO.toDomainModel(): List<Filmography> {
    return cast.map {
        Filmography(
            id = it.id,
            mediaType = it.media_type,
            posterPath = it.posterPath,
            name = it.titleName,
            releaseYear = it.releaseYear,
            character = it.character
        )
    }
}

fun List<FilmographyDTO>.concatToString(): String {
    return this.joinToString(", ") {
        "${it.titleName} (${it.releaseYear})"
    }
}