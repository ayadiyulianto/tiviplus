package com.pitchblack.tiviplus.ui.movie.detail

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pitchblack.tiviplus.data.model.*
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import com.pitchblack.tiviplus.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject constructor (
    private val repository: MovieRepository,
    @Assisted private val stateHandle: SavedStateHandle
) : ViewModel() {

    private var movieId = stateHandle.get<Int>("movieId")

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
        movieId?.let { initializeMovie(it) }
    }

    private fun initializeMovie(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getDetail(id)
                _movie.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeMovie", "FAILED! ${e.message}")
            }

            try {
                val response = repository.getCredits(id)
                _casts.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("getCast", "FAILED! ${e.message}")
            }

            try {
                val response = repository.getVideos(id)
                _videos.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("getVideos", "FAILED! ${e.message}")
            }

            try {
                val response = repository.getReviews(id)
                _reviews.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("getReviews", "FAILED! ${e.message}")
            }

            try {
                val response = repository.getRecommendations(id)
                _recommendations.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("getRecommend", "FAILED! ${e.message}")
            }

            try {
                val response = repository.getSimilar(id)
                _similar.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("getSimilar", "FAILED! ${e.message}")
            }
        }
    }

//    private fun initializeVideos(id: Int) {
//        viewModelScope.launch {
//            try {
//                val response = NetworkUtils.tmDbService.getMovieVideos(id)
//                _videos.value = response.toDomainModel()
//            } catch (e: Exception) {
//                Log.e("initializeVideos", "FAILED! ${e.message}")
//            }
//        }
//    }
//
//    private fun initializeCasts(id: Int) {
//        viewModelScope.launch {
//            try {
//                val response = NetworkUtils.tmDbService.getMovieCredits(id)
//                _casts.value = response.toDomainModel()
//            } catch (e: Exception) {
//                Log.e("initializeCast", "FAILED! ${e.message}")
//            }
//        }
//    }
//
//    private fun initializeReviews(id: Int) {
//        viewModelScope.launch {
//            try {
//                val response = NetworkUtils.tmDbService.getMovieReviews(id)
//                _reviews.value = response.toDomainModel()
//            } catch (e: Exception) {
//                Log.e("initializeReviews", "FAILED! ${e.message}")
//            }
//        }
//    }
//
//    private fun initializeRecommendations(id: Int) {
//        viewModelScope.launch {
//            try {
//                val response = NetworkUtils.tmDbService.getMovieRecommendations(id)
//                _recommendations.value = response.toDomainModel()
//            } catch (e: Exception) {
//                Log.e("initializeRecommend", "FAILED! ${e.message}")
//            }
//        }
//    }
//
//    private fun initializeSimilar(id: Int) {
//        viewModelScope.launch {
//            try {
//                val response = NetworkUtils.tmDbService.getMovieSimilar(id)
//                _similar.value = response.toDomainModel()
//            } catch (e: Exception) {
//                Log.e("initializeSimilar", "FAILED! ${e.message}")
//            }
//        }
//    }
}