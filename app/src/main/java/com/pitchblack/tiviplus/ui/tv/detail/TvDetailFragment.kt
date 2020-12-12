package com.pitchblack.tiviplus.ui.tv.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pitchblack.tiviplus.R

class TvDetailFragment : Fragment() {

    companion object {
        fun newInstance() = TvDetailFragment()
    }

    private lateinit var viewModel: TvDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TvDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}