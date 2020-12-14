package com.pitchblack.tiviplus.ui.tv.detail

import android.util.Log
import androidx.lifecycle.*
import com.pitchblack.tiviplus.data.model.*
import com.pitchblack.tiviplus.data.network.RestAPI
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import com.pitchblack.tiviplus.ui.movie.detail.MovieDetailViewModel
import kotlinx.coroutines.launch

class TvDetailViewModel(private val tvId: Int) : ViewModel() {

    private val _tv = MutableLiveData<TVDetail>()
    val tv: LiveData<TVDetail>
        get() = _tv

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>>
        get() = _videos

    private val _casts = MutableLiveData<List<Cast>>()
    val casts: LiveData<List<Cast>>
        get() = _casts

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>>
        get() = _reviews

    private val _recommendations = MutableLiveData<List<TV>>()
    val recommendations: LiveData<List<TV>>
        get() = _recommendations

    private val _similar = MutableLiveData<List<TV>>()
    val similar: LiveData<List<TV>>
        get() = _similar

    init {
        initializeMovie(tvId)
        initializeVideos(tvId)
        initializeCasts(tvId)
        initializeReviews(tvId)
        initializeRecommendations(tvId)
        initializeSimilar(tvId)
    }

    private fun initializeMovie(id: Int) {
        viewModelScope.launch {
            try {
                val response = RestAPI.tmDbService.getTVDetail(id)
                _tv.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeTV", "FAILED! ${e.message}")
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
                val response = RestAPI.tmDbService.getTvRecommendations(id)
                _recommendations.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeRecommend", "FAILED! ${e.message}")
            }
        }
    }

    private fun initializeSimilar(id: Int) {
        viewModelScope.launch {
            try {
                val response = RestAPI.tmDbService.getTvSimilar(id)
                _similar.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeSimilar", "FAILED! ${e.message}")
            }
        }
    }

    class Factory(private val tvId: Int): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (modelClass.isAssignableFrom(TvDetailViewModel::class.java)) {
                return TvDetailViewModel(tvId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}