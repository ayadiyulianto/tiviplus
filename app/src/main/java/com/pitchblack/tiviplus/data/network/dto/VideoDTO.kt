package com.pitchblack.tiviplus.data.network.dto

import com.pitchblack.tiviplus.data.model.Video
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoDTO(
    val id: String,
    val key: String = "",
    val name: String = "",
    val site: String = "",
    val type: String = ""
)

@JsonClass(generateAdapter = true)
data class VideosDTO(
    val results: List<VideoDTO>
)

fun VideosDTO.toDomainModel(): List<Video> {
    return results.map {
        Video(id = it.id, key = it.key, name = it.name)
    }
}