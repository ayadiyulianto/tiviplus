package com.pitchblack.tiviplus.ui.movie.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.Utils.getYear
import com.pitchblack.tiviplus.Utils.toHour
import com.pitchblack.tiviplus.data.model.MovieDetail
import com.pitchblack.tiviplus.data.network.RestAPI
import com.pitchblack.tiviplus.databinding.FragmentMovieDetailBinding
import com.pitchblack.tiviplus.ui.adapters.MovieGridAdapter
import com.pitchblack.tiviplus.ui.adapters.VideoListAdapter
import com.pitchblack.tiviplus.ui.adapters.CastsListAdapter
import com.pitchblack.tiviplus.ui.adapters.ReviewListAdapter
import java.text.NumberFormat
import java.util.*

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var videoListAdapter: VideoListAdapter
    private lateinit var castsListAdapter: CastsListAdapter
    private lateinit var reviewListAdapter: ReviewListAdapter
    private lateinit var recommendationGridAdapter: MovieGridAdapter
    private lateinit var similarGridAdapter: MovieGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        initToolbar()

        val arguments = MovieDetailFragmentArgs.fromBundle(requireArguments())
        viewModel = ViewModelProvider(this, MovieDetailViewModel.Factory(arguments.movieId))
            .get(MovieDetailViewModel::class.java)

        initAdapter()
        initObserver()

        return binding.root
    }

    private fun initToolbar() {
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        }
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener {
            (activity as AppCompatActivity).onBackPressed()
        }
    }

    private fun initAdapter() {
        videoListAdapter = VideoListAdapter()
        binding.includeTop.rvVideos.adapter = videoListAdapter
        castsListAdapter = CastsListAdapter()
        binding.includeTop.rvCasts.adapter = castsListAdapter
        reviewListAdapter = ReviewListAdapter()
        binding.includeBottom.rvReviews.adapter = reviewListAdapter
        recommendationGridAdapter = MovieGridAdapter()
        binding.includeBottom.rvRecommendations.adapter = recommendationGridAdapter
        similarGridAdapter = MovieGridAdapter()
        binding.includeBottom.rvSimilar.adapter = similarGridAdapter
    }

    private fun initObserver() {
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            it?.let {
                showToUI(it)
            }
        })
        viewModel.videos.observe(viewLifecycleOwner, Observer {
            it?.let {
                videoListAdapter.submitList(it)
            }
        })
        viewModel.casts.observe(viewLifecycleOwner, Observer {
            it?.let {
                castsListAdapter.submitList(it)
            }
        })
        viewModel.reviews.observe(viewLifecycleOwner, Observer {
            it?.let {
                reviewListAdapter.submitList(it)
            }
        })
        viewModel.recommendations.observe(viewLifecycleOwner, Observer {
            it?.let {
                recommendationGridAdapter.submitList(it)
            }
        })
        viewModel.similar.observe(viewLifecycleOwner, Observer {
            it?.let {
                similarGridAdapter.submitList(it)
            }
        })
    }

    private fun showToUI(movie: MovieDetail) {
        Glide.with(binding.root.context)
            .load(RestAPI.getPosterPath(movie.posterPath))
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.animation_loading)
                    .error(R.drawable.ic_baseline_broken_image_24))
            .into(binding.includeTop.imgDetailPoster)
        binding.includeTop.txtTitle.text = movie.title
        binding.includeTop.txtContentRating.text = getString(R.string.dummy_content_rating)
        binding.includeTop.txtStats.text =
            getString(R.string.stats, movie.releaseDate.getYear(), movie.runtime.toHour())
        binding.includeTop.txtGenre.text = movie.genres
        binding.includeTop.txtRating.text = movie.voteAverage.toString()
        binding.includeTop.txtRatingVotes.text = getString(R.string.rating_votes, movie.voteCount)
        binding.includeTop.txtTagline.text = movie.tagline
        binding.includeTop.txtOverview.text = movie.overview
        binding.includeDetails.txtOriTitleField.text = movie.originalTitle
        binding.includeDetails.txtReleaseDateField.text = movie.releaseDate
        binding.includeDetails.txtCountryField.text = movie.productionCountries
        binding.includeDetails.txtSpokenLangField.text = movie.spokenLanguages
        binding.includeDetails.txtCompanyField.text = movie.productionCompanies
        val formatter = NumberFormat.getCurrencyInstance(Locale("en", "US"))
        binding.includeDetails.txtBudgetField.text = formatter.format(movie.budget)
        binding.includeDetails.txtRevenueField.text = formatter.format(movie.revenue)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.appbar_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.search -> {
                Toast.makeText(activity, "Search", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.favourite -> {
                Toast.makeText(activity, "Favourite", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.watchlist -> {
                Toast.makeText(activity, "Watchlist", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.share -> {
                Toast.makeText(activity, "Share", Toast.LENGTH_SHORT).show()
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