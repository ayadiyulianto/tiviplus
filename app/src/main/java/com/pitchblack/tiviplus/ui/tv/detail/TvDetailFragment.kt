package com.pitchblack.tiviplus.ui.tv.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.utils.DataUtils.getYear
import com.pitchblack.tiviplus.data.model.TVDetail
import com.pitchblack.tiviplus.utils.NetworkUtils
import com.pitchblack.tiviplus.databinding.FragmentTvDetailBinding
import com.pitchblack.tiviplus.ui.commons.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvDetailFragment : Fragment() {

    private var _binding: FragmentTvDetailBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: TvDetailViewModel by viewModels()
    private lateinit var videoListAdapter: VideoListAdapter
    private lateinit var castsListAdapter: CastsListAdapter
    private lateinit var seasonListAdapter: SeasonListAdapter
    private lateinit var reviewListAdapter: ReviewListAdapter
    private lateinit var recommendationGridAdapter: TVGridAdapter
    private lateinit var similarGridAdapter: TVGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvDetailBinding.inflate(inflater, container, false)

        initToolbar()
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
        setHasOptionsMenu(true)
    }

    private fun initAdapter() {
        videoListAdapter = VideoListAdapter()
        binding.includeTop.rvVideos.adapter = videoListAdapter
        castsListAdapter = CastsListAdapter()
        binding.includeTop.rvCasts.adapter = castsListAdapter
        seasonListAdapter = SeasonListAdapter()
        binding.includeSeason.rvSeasons.adapter = seasonListAdapter
        reviewListAdapter = ReviewListAdapter()
        binding.includeBottom.rvReviews.adapter = reviewListAdapter
        recommendationGridAdapter = TVGridAdapter()
        binding.includeBottom.rvRecommendations.adapter = recommendationGridAdapter
        similarGridAdapter = TVGridAdapter()
        binding.includeBottom.rvSimilar.adapter = similarGridAdapter
    }

    private fun initObserver() {
        viewModel.tv.observe(viewLifecycleOwner, Observer {
            it?.let {
                showToUI(it)
                seasonListAdapter.submitList(it.seasons)
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

    private fun showToUI(tv: TVDetail) {
        Glide.with(binding.root.context)
            .load(NetworkUtils.getPosterPath(tv.posterPath))
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.animation_loading)
                    .error(R.drawable.ic_baseline_broken_image_24))
            .into(binding.includeTop.imgDetailPoster)
        binding.includeTop.txtTitle.text = tv.name
        binding.includeTop.txtContentRating.text = getString(R.string.dummy_content_rating)
        binding.includeTop.txtStats.text =
            getString(R.string.stats, tv.firstAirDate.getYear(), "${tv.episodeRunTime}m")
        binding.includeTop.txtGenre.text = tv.genres
        binding.includeTop.txtRating.text = tv.voteAverage.toString()
        binding.includeTop.txtRatingVotes.text = getString(R.string.rating_votes, tv.voteCount)
        binding.includeTop.txtTagline.text = tv.tagline
        binding.includeTop.txtOverview.text = tv.overview
        binding.includeDetails.txtOriTitleField.text = tv.originalName
        binding.includeDetails.txtCreatorField.text = tv.createdBy
        binding.includeDetails.txtReleaseDateField.text = tv.firstAirDate
        binding.includeDetails.txtLastDateField.text = tv.lastAirDate
        binding.includeDetails.txtNumberSeasonField.text = tv.numberOfSeasons.toString()
        binding.includeDetails.txtNumberEpisodeField.text = tv.numberOfEpisodes.toString()
        binding.includeDetails.txtCountryField.text = tv.originCountry
        binding.includeDetails.txtSpokenLangField.text = tv.languages
        binding.includeDetails.txtCompanyField.text = tv.productionCompanies
        binding.includeDetails.txtNetworksField.text = tv.networks
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