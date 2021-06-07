package com.pitchblack.tiviplus.data.network.dto

data class ImageDTO(
    val file_path : String? = ""
) {
    val filePath = file_path ?: ""
}
