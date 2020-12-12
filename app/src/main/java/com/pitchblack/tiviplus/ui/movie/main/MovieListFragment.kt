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
import com.pitchblack.tiviplus.ui.people.main.PeopleAdapter

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var adapter: MovieListAdapter

    companion object {
        private const val ARG_TYPE = "tab"
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
        movieListViewModel = ViewModelProvider(this, MovieListViewModel.Factory(tabId))
            .get(getString(tabId), MovieListViewModel::class.java)
        adapter = MovieListAdapter(MovieClickListener {
            movieListViewModel.onMovieItemClicked(it)
        })

        binding.rvMainMovie.layoutManager = LinearLayoutManager(activity)
        binding.rvMainMovie.adapter = adapter
        movieListViewModel.listMovie.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        movieListViewModel.movieIdSelected.observe(viewLifecycleOwner, Observer {
            it?.let {
                (parentFragment as MovieFragment).navigateToDetailFragment(it)
                movieListViewModel.onMovieDetailNavigated()
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}