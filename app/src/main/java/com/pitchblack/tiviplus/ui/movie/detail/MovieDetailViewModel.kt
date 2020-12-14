package com.pitchblack.tiviplus.ui.movie.detail

import android.util.Log
import androidx.lifecycle.*
import com.pitchblack.tiviplus.data.model.*
import com.pitchblack.tiviplus.data.network.RestAPI
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieId: Int = 0) : ViewModel() {

    private val _movie = MutableLiveData<MovieDetail>()
    val movie: LiveData<MovieDetail>
        get() = _movie

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>>
        get() = _videos

    private val _casts = MutableLiveData<List<Cast>>()
    val casts: LiveData<List<Cast>>
        get() = _casts

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>>
        get() = _reviews

    private val _recommendations = MutableLiveData<List<Movie>>()
    val recommendations: LiveData<List<Movie>>
        get() = _recommendations

    private val _similar = MutableLiveData<List<Movie>>()
    val similar: LiveData<List<Movie>>
        get() = _similar

    init {
        initializeMovie(movieId)
        initializeVideos(movieId)
        initializeCasts(movieId)
        initializeReviews(movieId)
        initializeRecommendations(movieId)
        initializeSimilar(movieId)
    }

    private fun initializeMovie(id: Int) {
        viewModelScope.launch {
            try {
                val response = RestAPI.tmDbService.getMovieDetail(id)
                _movie.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeMovie", "FAILED! ${e.message}")
            }
        }
    }

    private fun initializeVideos(id: Int) {
        viewModelScope.launch {
            try {
                val response = RestAPI.tmDbService.getMovieVideos(id)
                _videos.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeVideos", "FAILED! ${e.message}")
            }
        }
    }

    private fun initializeCasts(id: Int) {
        viewModelScope.launch {
            try {
                val response = RestAPI.tmDbService.getMovieCredits(id)
                _casts.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeCast", "FAILED! ${e.message}")
            }
        }
    }

    private fun initializeReviews(id: Int) {
        viewModelScope.launch {
            try {
                val response = RestAPI.tmDbService.getMovieReviews(id)
                _reviews.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeReviews", "FAILED! ${e.message}")
            }
        }
    }

    private fun initializeRecommendations(id: Int) {
        viewModelScope.launch {
            try {
                val response = RestAPI.tmDbService.getMovieRecommendations(id)
                _recommendations.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeRecommend", "FAILED! ${e.message}")
            }
        }
    }

    private fun initializeSimilar(id: Int) {
        viewModelScope.launch {
            try {
                val response = RestAPI.tmDbService.getMovieSimilar(id)
                _similar.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeSimilar", "FAILED! ${e.message}")
            }
        }
    }

    class Factory(private val movieId: Int): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
                return MovieDetailViewModel(movieId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}