package com.pitchblack.tiviplus.ui.movie.main

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pitchblack.tiviplus.data.repository.MovieRepository
import com.pitchblack.tiviplus.data.model.Movie
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import com.pitchblack.tiviplus.ui.movie.main.MovieListFragment.Companion.ARG_TYPE
import kotlinx.coroutines.launch

class MovieListViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tabId = savedStateHandle.get<Int>(ARG_TYPE)
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
                _listMovies.value = repository.getList(type)
            } catch (e: Exception) {
                Log.e("initializeMovieList", "failed : ${e.message}")
            }
        }
    }
}