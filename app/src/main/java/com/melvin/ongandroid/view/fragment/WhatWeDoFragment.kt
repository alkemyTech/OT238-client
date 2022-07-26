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
import com.melvin.ongandroid.view.adapters.WhatWeDoAdapter
import com.melvin.ongandroid.viewmodel.ApiStatus
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
        charging()
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
        viewModel.whatWeDoList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                snackBar()
            } else {
                initRecyclerView(it)
            }
        }
    }

    private fun charging() {
        viewModel.status.observe(viewLifecycleOwner) { currentStatus ->
            when (currentStatus) {
                ApiStatus.SUCCESS, ApiStatus.FAILURE  -> binding.pbWhatWeDo.hideProgressBar()
                ApiStatus.LOADING -> binding.pbWhatWeDo.showProgressBar()
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