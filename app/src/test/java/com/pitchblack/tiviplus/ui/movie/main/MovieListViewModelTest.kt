package com.pitchblack.tiviplus.ui.movie.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.pitchblack.tiviplus.data.model.Movie
import com.pitchblack.tiviplus.data.repository.MovieRepository
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
class MovieListViewModelTest {

    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var movieListViewModel: MovieListViewModel
    
    @Before
    fun setup() {
        movieListViewModel = MovieListViewModel(repository, savedStateHandle)
    }

    @Test
    fun getListMovie() {
        val movieList = movieListViewModel.listMovie
        val movies = MutableLiveData<List<Movie>>()
        //movies.value =

        //assertNotNull(movieList)
        //assertTrue("Data more than 10 rows", movieList > 10)
    }
}