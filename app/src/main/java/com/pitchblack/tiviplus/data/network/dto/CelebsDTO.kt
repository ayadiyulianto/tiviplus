package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.Celebs
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeopleDTO (
    val id: Int,
    val popularity: Double = 0.0,
    val known_for_department: String = "",
    val name: String = "",
    val profile_path: String? = "",
    val filmography: List<FilmographyDTO>
) {
    val profilePath = profile_path ?: ""
}

@JsonClass(generateAdapter = true)
data class PeopleListDTO(
    val results: List<PeopleDTO>
)

fun PeopleListDTO.toDomainModel(): List<Celebs> {
    return results.map {
        Celebs(
            id = it.id,
            department = it.known_for_department,
            name = it.name,
            profilePath = it.profilePath,
            knownFor = it.filmography.concatToString()
        )
    }
}