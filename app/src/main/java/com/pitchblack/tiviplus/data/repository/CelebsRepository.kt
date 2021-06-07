package com.pitchblack.tiviplus.data.repository

import com.pitchblack.tiviplus.data.network.TMDbService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CelebsRepository @Inject constructor(private val tmDbService: TMDbService) {
    suspend fun getPopular() = tmDbService.getPopularPeople()
    suspend fun getDetail(id: Int) = tmDbService.getPeopleDetail(id)
    suspend fun getFilmography(id: Int) = tmDbService.getPeopleFilmography(id)
    suspend fun getProfileImages(id: Int) = tmDbService.getPeopleImages(id)
}