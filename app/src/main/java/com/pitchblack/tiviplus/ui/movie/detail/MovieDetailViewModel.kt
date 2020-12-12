package com.pitchblack.tiviplus.ui.movie.detail

import android.util.Log
import androidx.lifecycle.*
import com.pitchblack.tiviplus.data.model.MovieDetail
import com.pitchblack.tiviplus.data.network.RestAPI
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieId: Int = 0) : ViewModel() {

    private val _movie = MutableLiveData<MovieDetail>()
    val movie: LiveData<MovieDetail>
        get() = _movie

    init {
        initializeMovie(movieId)
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