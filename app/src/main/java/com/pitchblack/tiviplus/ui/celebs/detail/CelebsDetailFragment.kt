package com.pitchblack.tiviplus.ui.celebs.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.data.model.CelebsDetail
import com.pitchblack.tiviplus.databinding.FragmentPeopleDetailBinding
import com.pitchblack.tiviplus.ui.commons.FilmographyListAdapter
import com.pitchblack.tiviplus.ui.commons.ImagesAdapter
import com.pitchblack.tiviplus.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CelebsDetailFragment : Fragment() {

    private var _binding : FragmentPeopleDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CelebsDetailViewModel by viewModels()
    private lateinit var filmographyAdapter : FilmographyListAdapter
    private lateinit var imagesAdapter : ImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleDetailBinding.inflate(inflater, container, false)

        initToolbar()
        initAdapters()
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

    private fun initAdapters() {
        filmographyAdapter = FilmographyListAdapter()
        binding.rvFilmography.adapter = filmographyAdapter
        imagesAdapter = ImagesAdapter()
        binding.rvProfile.adapter = imagesAdapter
    }

    private fun initObserver() {
        viewModel.celebsDetail.observe(viewLifecycleOwner, Observer {
            it?.let {
                showDetail(it)
            }
        })
        viewModel.filmography.observe(viewLifecycleOwner, Observer {
            it?.let {
                filmographyAdapter.submitList(it)
            }
        })
        viewModel.profileImages.observe(viewLifecycleOwner, Observer {
            it?.let {
                imagesAdapter.submitList(it)
            }
        })
    }

    private fun showDetail(celebs: CelebsDetail) {
        Glide.with(binding.root.context)
            .load(NetworkUtils.getProfilePath(celebs.profilePath))
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.animation_loading)
                    .error(R.drawable.ic_baseline_broken_image_24))
            .into(binding.imgDetailProfile)
        binding.txtPeopleName.text = celebs.name
        binding.txtPeopleDepartment.text = celebs.department
        binding.txtBorn.text = binding.root.context.getString(R.string.born, celebs.birthday)
        binding.txtPlaceofbirth.text = binding.root.context.getString(R.string.place_of_birth, celebs.placeOfBirth)
        binding.txtBiography.text = celebs.biography
    }

}