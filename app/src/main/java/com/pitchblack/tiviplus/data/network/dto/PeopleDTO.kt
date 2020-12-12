package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.Utils.getYear
import com.pitchblack.tiviplus.data.model.People
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeopleDTO (
    val id: Int,
    val popularity: Double = 0.0,
    val known_for_department: String = "",
    val name: String = "",
    val profile_path: String = "",
    val known_for: List<KnownForDTO>
)

@JsonClass(generateAdapter = true)
data class PeopleListDTO(
    val results: List<PeopleDTO>
)

@JsonClass(generateAdapter = true)
data class KnownForDTO (
    val media_type: String = "",
    val name: String = "",
    val first_air_date: String = "",
    val title: String = "",
    val release_date: String = ""
) {
    val titleName: String = if (media_type == "movie") title else name
    val releaseYear: String =
        if (media_type == "movie") release_date.getYear() else first_air_date.getYear()
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