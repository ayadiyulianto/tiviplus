package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.CelebsDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeopleDetailDTO (
    val id: Int,
    val known_for_department: String = "",
    val name: String = "",
    val biography: String = "",
    val profile_path: String? = "",
    val birthday: String = "",
    val place_of_birth: String = ""
) {
    val profilePath = profile_path ?: ""
}

fun PeopleDetailDTO.toDomainModel(): CelebsDetail {
    return CelebsDetail(
        id = id,
        department = known_for_department,
        name = name,
        biography = biography,
        profilePath = profilePath,
        birthday = birthday,
        placeOfBirth = place_of_birth
    )
}