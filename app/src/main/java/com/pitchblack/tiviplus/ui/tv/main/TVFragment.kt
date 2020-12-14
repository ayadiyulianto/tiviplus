package com.pitchblack.tiviplus.ui.tv.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.databinding.FragmentTvBinding
import com.pitchblack.tiviplus.ui.movie.main.MovieFragmentDirections
import com.pitchblack.tiviplus.ui.people.main.PeopleAdapter

class TVFragment : Fragment() {

    private var _binding: FragmentTvBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var tvPagerAdapter: TVPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        if(activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        }
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowHomeEnabled(true)
            setIcon(R.drawable.ic_baseline_local_movies_24)
        }

        tvPagerAdapter = TVPagerAdapter(this)
        binding.viewPager.adapter = tvPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getString(com.pitchblack.tiviplus.ui.movie.main.TAB_TITLES[position])
        }.attach()

        return binding.root
    }

    fun navigateToDetailFragment(tvId: Int) {
        findNavController().navigate(
            TVFragmentDirections.actionNavigationTvToTvDetailFragment(tvId)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_appbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.search -> {
                Toast.makeText(activity, "Search", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.myaccount -> {
                Toast.makeText(activity, "Search", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}