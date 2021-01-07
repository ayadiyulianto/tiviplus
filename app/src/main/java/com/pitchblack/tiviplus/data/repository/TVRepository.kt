package com.pitchblack.tiviplus.data.repository

import com.pitchblack.tiviplus.data.network.TMDbService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVRepository @Inject constructor(private val tmDbService: TMDbService) {
    suspend fun getList(type: String) = tmDbService.getTvList(type)
    suspend fun getDetail(id: Int) = tmDbService.getTvDetail(id)
    suspend fun getCredits(id: Int) = tmDbService.getTvCredits(id)
    suspend fun getVideos(id: Int) = tmDbService.getTvVideos(id)
    suspend fun getReviews(id: Int) = tmDbService.getTvReviews(id)
    suspend fun getRecommendations(id: Int) = tmDbService.getTvRecommendations(id)
    suspend fun getSimilar(id: Int) = tmDbService.getTvSimilar(id)
}