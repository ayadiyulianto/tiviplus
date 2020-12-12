package com.pitchblack.tiviplus.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RestAPI {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"

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

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .cache(null)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        //.client(okHttpClient)
        .build()

    val tmDbService : TMDbService by lazy {
        retrofit.create<TMDbService>(TMDbService::class.java)
    }
}