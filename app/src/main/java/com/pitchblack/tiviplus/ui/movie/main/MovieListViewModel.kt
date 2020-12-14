package com.pitchblack.tiviplus.ui.movie.main

import android.util.Log
import androidx.lifecycle.*
import com.pitchblack.tiviplus.data.network.RestAPI
import com.pitchblack.tiviplus.data.model.Movie
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import kotlinx.coroutines.launch

class MovieListViewModel(private val tabId: Int = TAB_TITLES[0]) : ViewModel() {

    private val _listMovies = MutableLiveData<List<Movie>>()
    val listMovie: LiveData<List<Movie>>
        get() = _listMovies

    init {
        when(tabId) {
            TAB_TITLES[0] -> initializeMovieList("popular")
            TAB_TITLES[1] -> initializeMovieList("top_rated")
            TAB_TITLES[2] -> initializeMovieList("upcoming")
            TAB_TITLES[3] -> initializeMovieList("now_playing")
        }
    }

    private fun initializeMovieList(type: String) {
        viewModelScope.launch {
            try {
                val movieList = RestAPI.tmDbService.getMovieList(type)
                _listMovies.value = movieList.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeMovieList", "failed : ${e.message}")
            }
        }
    }

    class Factory(private val tabId: Int) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(p0: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (p0.isAssignableFrom(MovieListViewModel::class.java)) {
                return MovieListViewModel(tabId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}