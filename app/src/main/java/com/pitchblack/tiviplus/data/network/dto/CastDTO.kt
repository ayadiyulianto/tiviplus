package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.Cast
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastDTO(
    val id: Int,
    val name: String = "",
    val profile_path: String? = "",
    val character: String = "",
    val order: Int = 0
) {
    val profilePath = profile_path ?: ""
}

@JsonClass(generateAdapter = true)
data class CreditsDTO(
    val cast: List<CastDTO>,
)

fun CreditsDTO.toDomainModel() : List<Cast> {
    return cast.map {
        Cast(
            id = it.id,
            name = it.name,
            profilePath = it.profilePath,
            character = it.character
        )
    }
}