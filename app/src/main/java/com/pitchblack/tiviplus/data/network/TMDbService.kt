package com.pitchblack.tiviplus.data.network

import com.pitchblack.tiviplus.BuildConfig
import com.pitchblack.tiviplus.data.model.TVDetail
import com.pitchblack.tiviplus.data.network.dto.*
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDbService {
    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }

    // Movie Endpoints

    @GET("movie/{type}?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getMovieList(@Path("type") type: String): MovieListDTO

    @GET("movie/{id}?api_key=$API_KEY&language=en-US")
    suspend fun getMovieDetail(@Path("id") id: Int): MovieDetailDTO

    @GET("movie/{id}/credits?api_key=$API_KEY&language=en-US")
    suspend fun getMovieCredits(@Path("id") id: Int): CreditsDTO

    @GET("movie/{id}/videos?api_key=$API_KEY&language=en-US")
    suspend fun getMovieVideos(@Path("id") id: Int): VideoListDTO

    @GET("movie/{id}/reviews?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getMovieReviews(@Path("id") id: Int): ReviewListDTO

    @GET("movie/{id}/recommendations?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getMovieRecommendations(@Path("id") id: Int): MovieListDTO

    @GET("movie/{id}/similar?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getMovieSimilar(@Path("id") id: Int): MovieListDTO

    // TV Show Endpoints

    @GET("tv/{type}?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getTVList(@Path("type") type: String): TvListDTO

    @GET("tv/{id}?api_key=$API_KEY&language=en-US")
    suspend fun getTVDetail(@Path("id") id: Int): TvDetailDTO

    @GET("tv/{id}/credits?api_key=$API_KEY&language=en-US")
    suspend fun getTvCredits(@Path("id") id: Int): CreditsDTO

    @GET("tv/{id}/videos?api_key=$API_KEY&language=en-US")
    suspend fun getTvVideos(@Path("id") id: Int): VideoListDTO

    @GET("tv/{id}/reviews?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getTvReviews(@Path("id") id: Int): ReviewListDTO

    @GET("tv/{id}/recommendations?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getTvRecommendations(@Path("id") id: Int): TvListDTO

    @GET("tv/{id}/similar?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getTvSimilar(@Path("id") id: Int): TvListDTO

    // People Endpoints

    @GET("person/popular?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getPopularPeople(): PeopleListDTO

    @GET("person/{id}?api_key=$API_KEY&language=en-US")
    suspend fun getPeopleDetail(@Path("id") id: Int): PeopleListDTO
}