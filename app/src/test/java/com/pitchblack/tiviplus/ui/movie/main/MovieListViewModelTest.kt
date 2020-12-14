package com.pitchblack.tiviplus.ui.movie.main

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieListViewModelTest {

    private lateinit var movieListViewModel: MovieListViewModel
    
    @Before
    fun setup() {
        movieListViewModel = MovieListViewModel(TAB_TITLES[0])
    }

    @Test
    fun getListMovie() {
        val movieList = movieListViewModel.listMovie
        assertNotNull(movieList)
        //assertTrue("Data more than 10 rows", movieList > 10)
    }
}