package com.pitchblack.tiviplus.ui.movie.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pitchblack.tiviplus.R

val TAB_TITLES = arrayOf(
    R.string.popular,
    R.string.top_rated,
    R.string.upcoming,
    R.string.now_playing
)

class MoviePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return MovieListFragment.newInstance(TAB_TITLES[position])
    }
}