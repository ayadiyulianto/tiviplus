package com.pitchblack.tiviplus.ui.tv.detail

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pitchblack.tiviplus.data.model.*
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import com.pitchblack.tiviplus.data.repository.TVRepository
import kotlinx.coroutines.launch

class TvDetailViewModel @ViewModelInject constructor(
    private val repository: TVRepository,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tvId = savedStateHandle.get<Int>("tvId")
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
        tvId?.let { initializeTv(it) }
    }

    private fun initializeTv(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getDetail(id)
                _tv.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeTV", "FAILED! ${e.message}")
            }

            try {
                val response = repository.getCredits(id)
                _casts.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeCast", "FAILED! ${e.message}")
            }

            try {
                val response = repository.getVideos(id)
                _videos.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeTv", "FAILED! ${e.message}")
            }

            try {
                val response = repository.getReviews(id)
                _reviews.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeTv", "FAILED! ${e.message}")
            }

            try {
                val response = repository.getRecommendations(id)
                _recommendations.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeTv", "FAILED! ${e.message}")
            }

            try {
                val response = repository.getSimilar(id)
                _similar.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeTv", "FAILED! ${e.message}")
            }
        }
    }

//    private fun initializeVideos(id: Int) {
//        viewModelScope.launch {
//            try {
//                val response = NetworkUtils.tmDbService.getTvVideos(id)
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
//                val response = NetworkUtils.tmDbService.getTvCredits(id)
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
//                val response = NetworkUtils.tmDbService.getTvReviews(id)
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
//                val response = NetworkUtils.tmDbService.getTvRecommendations(id)
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
//                val response = NetworkUtils.tmDbService.getTvSimilar(id)
//                _similar.value = response.toDomainModel()
//            } catch (e: Exception) {
//                Log.e("initializeSimilar", "FAILED! ${e.message}")
//            }
//        }
//    }
}