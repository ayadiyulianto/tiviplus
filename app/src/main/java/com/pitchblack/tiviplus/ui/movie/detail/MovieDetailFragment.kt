package com.pitchblack.tiviplus.ui.movie.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
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
import com.pitchblack.tiviplus.ui.detail.MovieGridAdapter
import com.pitchblack.tiviplus.ui.detail.VideoListAdapter
import com.pitchblack.tiviplus.ui.detail.CastsListAdapter
import com.pitchblack.tiviplus.ui.detail.ReviewListAdapter

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

        val arguments = MovieDetailFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(context, "id : ${arguments.movieId}", Toast.LENGTH_LONG).show()
        viewModel = ViewModelProvider(this, MovieDetailViewModel.Factory(arguments.movieId))
            .get(MovieDetailViewModel::class.java)

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

        viewModel.movie.observe(viewLifecycleOwner, Observer {
            it?.let {
                showToUI(it)
            }
        })

        return binding.root
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
        videoListAdapter.submitList(movie.videos)
        castsListAdapter.submitList(movie.credits)
        reviewListAdapter.submitList(movie.reviews)
        binding.includeBottom.viewgroupFacts.txtOriTitleField.text = movie.originalTitle
        binding.includeBottom.viewgroupFacts.txtOriLangField.text = movie.originalLanguage
        recommendationGridAdapter.submitList(movie.recommendations)
        similarGridAdapter.submitList(movie.similar)
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