package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.People

data class PeopleDTO (
    val id: Int,
    val known_for_department: String,
    val name: String,
    val profile_path: String,
    val known_for: List<KnownForDTO>
)

data class PeopleListDTO(
    val results: List<PeopleDTO>
)

data class KnownForDTO (
    val media_type: String,
    val name: String = "",
    val first_air_date: String = "",
    val title: String = "",
    val release_date: String = ""
) {
    val titleName: String = if (media_type == "movie") title else name
    val releaseYear: String =
        if (media_type == "movie") {
            if (release_date.isEmpty()) "-" else release_date.take(4)
        } else {
            if (first_air_date.isEmpty()) "-" else first_air_date.take(4)
        }
}

fun PeopleListDTO.toDomainModel(): List<People> {
    return results.map {
        People(
            id = it.id,
            department = it.known_for_department,
            name = it.name,
            profilePath = it.profile_path,
            knownFor = it.known_for.concatToString()
        )
    }
}

fun List<KnownForDTO>.concatToString(): String {
    return this.joinToString(", ") {
        "${it.titleName} (${it.releaseYear})"
    }
}