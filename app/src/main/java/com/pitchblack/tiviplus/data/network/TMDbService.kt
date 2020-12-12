package com.pitchblack.tiviplus.data.network

import com.pitchblack.tiviplus.BuildConfig
import com.pitchblack.tiviplus.data.model.MovieDetail
import com.pitchblack.tiviplus.data.model.TVDetail
import com.pitchblack.tiviplus.data.network.dto.MovieDetailDTO
import com.pitchblack.tiviplus.data.network.dto.MovieListDTO
import com.pitchblack.tiviplus.data.network.dto.PeopleListDTO
import com.pitchblack.tiviplus.data.network.dto.TvListDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDbService {
    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }

    @GET("movie/{type}?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getMovieList(@Path("type") type: String): MovieListDTO

    @GET("movie/{id}?api_key=$API_KEY&language=en-US" +
            "&append_to_response=release_dates,credits,videos,reviews,recommendations,similar")
    suspend fun getMovieDetail(@Path("id") id: Int): MovieDetailDTO

    @GET("tv/{type}?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getTVList(@Path("type") type: String): TvListDTO

    @GET("tv/{id}?api_key=$API_KEY&language=en-US" +
            "&append_to_response=content_ratings,credits,videos,reviews,recommendations,similar")
    suspend fun getTVDetail(@Path("id") id: Int): TVDetail

    @GET("person/popular?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getPopularPeople(): PeopleListDTO

    @GET("person/{id}?api_key=$API_KEY&language=en-US" +
            "&append_to_response=combined_credits,images")
    suspend fun getPeopleDetail(@Path("id") id: Int): PeopleListDTO
}