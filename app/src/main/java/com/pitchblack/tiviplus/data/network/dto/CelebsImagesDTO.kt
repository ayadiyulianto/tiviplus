package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.Image

data class PeopleImagesDTO(
    val profiles : List<ImageDTO>
)

fun PeopleImagesDTO.toDomainModel() : List<Image> {
    return profiles.map {
        Image(filePath = it.filePath)
    }
}