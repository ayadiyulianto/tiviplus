package com.pitchblack.tiviplus.ui.celebs.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.databinding.FragmentPeopleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CelebsFragment : Fragment() {

    private var _binding: FragmentPeopleBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: CelebsViewModel by viewModels()
    private lateinit var adapter: PeopleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)

        setToolbar()
        setHasOptionsMenu(true)
        adapter = PeopleAdapter(PeopleClickListener {
            navigateToDetailFragment(it)
        })
        binding.rvMainPeople.adapter = adapter
        binding.rvMainPeople.layoutManager = LinearLayoutManager(activity)
        viewModel.listCelebs.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    private fun setToolbar() {
        if(activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        }
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowHomeEnabled(true)
            setIcon(R.drawable.ic_baseline_local_movies_24)
        }
    }

    fun navigateToDetailFragment(peopleId: Int) {
        findNavController().navigate(
            PeopleFragmentDirections.actionNavigationPeopleToPeopleDetailFragment(peopleId)
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