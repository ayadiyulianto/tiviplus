package com.pitchblack.tiviplus.utils

object NetworkUtils {

    val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"

    fun getPosterPath(url: String): String {
        return "$IMAGE_BASE_URL/w154$url"
    }

    fun getBackdropPath(url: String): String {
        return "$IMAGE_BASE_URL/w780$url"
    }

    fun getProfilePath(url: String): String {
        return "$IMAGE_BASE_URL/w185$url"
    }

    fun getYoutubePlaceholder(key: String): String {
        return "https://img.youtube.com/vi/$key/mqdefault.jpg"
    }
}