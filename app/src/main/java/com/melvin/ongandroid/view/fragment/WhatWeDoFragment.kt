package com.melvin.ongandroid.view.fragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentWhatwedoBinding
import com.melvin.ongandroid.model.entities.whatWeDo.WhatWeDo
import com.melvin.ongandroid.view.adapters.NewsAdapter
import com.melvin.ongandroid.view.adapters.WhatWeDoAdapter
import com.melvin.ongandroid.viewmodel.WhatWeDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WhatWeDoFragment : Fragment() {

    private var _binding: FragmentWhatwedoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WhatWeDoViewModel by viewModels()
    private lateinit var adapter: WhatWeDoAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWhatwedoBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.menu_activities)
        setupWhatWeDo()
    }


    private fun initRecyclerView(data: List<WhatWeDo>) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val manager = GridLayoutManager(binding.root.context, 2, GridLayoutManager.VERTICAL, false)
            binding.rvActivities.layoutManager = manager
        } else {
            binding.rvActivities.layoutManager = LinearLayoutManager(context)
            binding.rvActivities.layoutManager = LinearLayoutManager(binding.root.context)
        }
        binding.rvActivities.adapter = WhatWeDoAdapter(data, false)

    }

    private fun setupWhatWeDo() {
        viewModel.setWhatWeDo()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.observerWhatWeDoList().observe(viewLifecycleOwner) {
            if (it != null) {
                charging()
                initRecyclerView(it)
            } else {
                snackBar()
            }
        }
    }

    private fun charging() {
        viewModel.charging.observe(viewLifecycleOwner) { charging ->
            if (charging) {
                binding.pbWhatWeDo.visibility = View.VISIBLE
            } else {
                binding.pbWhatWeDo.visibility = View.GONE
            }
        }
    }

    private fun snackBar() {
        Snackbar.make(binding.activitieConstraint, R.string.textError, Snackbar.LENGTH_LONG)
            .setAction(R.string.actionText) {
                setupObserver()
            }
            .show()
    }
}