package com.pitchblack.tiviplus.ui.tv.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pitchblack.tiviplus.databinding.FragmentTvListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVListFragment : Fragment() {

    private var _binding: FragmentTvListBinding? = null
    private val binding get() = _binding!!

    private lateinit var tvListViewModel: TVListViewModel
    private lateinit var adapter: TVListAdapter

    companion object {
        private const val ARG_TYPE = "type"
        @JvmStatic
        fun newInstance(tabId: Int): TVListFragment {
            return TVListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TYPE, tabId)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvListBinding.inflate(inflater, container, false)

        val tabId = arguments?.getInt(ARG_TYPE) ?: TAB_TITLES[0]
        tvListViewModel = ViewModelProvider(this).get(getString(tabId), TVListViewModel::class.java)

        adapter =TVListAdapter(TvItemClickListener {
            (parentFragment as TVFragment).navigateToDetailFragment(it)
        })
        binding.rvMainTv.layoutManager = LinearLayoutManager(activity)
        binding.rvMainTv.adapter = adapter
        tvListViewModel.listTV.observe(viewLifecycleOwner, Observer {
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