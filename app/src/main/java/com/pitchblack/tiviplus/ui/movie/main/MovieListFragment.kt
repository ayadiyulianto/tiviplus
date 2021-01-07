package com.pitchblack.tiviplus.ui.movie.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pitchblack.tiviplus.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var adapter: MovieListAdapter

    companion object {
        const val ARG_TYPE = "tab"
        @JvmStatic
        fun newInstance(tab: Int): MovieListFragment {
            return MovieListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TYPE, tab)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)

        val tabId = arguments?.getInt(ARG_TYPE) ?: TAB_TITLES[0]
        movieListViewModel = ViewModelProvider(this).get(getString(tabId), MovieListViewModel::class.java)

        adapter = MovieListAdapter(MovieClickListener {
            (parentFragment as MovieFragment).navigateToDetailFragment(it)
        })
        binding.rvMainMovie.layoutManager = LinearLayoutManager(activity)
        binding.rvMainMovie.adapter = adapter
        movieListViewModel.listMovie.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}