package com.pitchblack.tiviplus.data.repository

import androidx.lifecycle.LiveData
import com.pitchblack.tiviplus.AppExecutors
import com.pitchblack.tiviplus.data.localdb.AppDatabase
import com.pitchblack.tiviplus.data.localdb.MovieDao
import com.pitchblack.tiviplus.data.model.Movie
import com.pitchblack.tiviplus.data.network.ApiResponse
import com.pitchblack.tiviplus.data.network.TMDbService
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import com.pitchblack.tiviplus.utils.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieMoviesitory @Inject constructor(
    private val appExecutors: AppExecutors,
    private val db: AppDatabase,
    private val movieDao: MovieDao,
    private val tmDbService: TMDbService
) {

    private val movieListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    suspend fun getList(type: String): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<Movie>>(appExecutors) {
            override fun saveCallResult(item: List<Movie>) {
                movieDao.insert(item)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty() || movieListRateLimit.shouldFetch(type)
            }

            override fun loadFromDb(): LiveData<List<Movie>> {
                movieDao.getPopular()
            }

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> {
                val list = tmDbService.getMovieList(type)
                return list.toDomainModel()
            }

            override fun onFetchFailed() = movieListRateLimit.reset(type)

        }.asLiveData()
    }
    suspend fun getDetail(id: Int) = tmDbService.getMovieDetail(id)
    suspend fun getCredits(id: Int) = tmDbService.getMovieCredits(id)
    suspend fun getVideos(id: Int) = tmDbService.getMovieVideos(id)
    suspend fun getReviews(id: Int) = tmDbService.getMovieReviews(id)
    suspend fun getRecommendations(id: Int) = tmDbService.getMovieRecommendations(id)
    suspend fun getSimilar(id: Int) = tmDbService.getMovieSimilar(id)
}