package com.melvin.ongandroid.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentActivitieBinding
import com.melvin.ongandroid.model.entities.activities.Activitie
import com.melvin.ongandroid.view.adapters.ActivitieAdapter
import com.melvin.ongandroid.viewmodel.ActivitiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ActivitieFragment : Fragment() {

    private var _binding: FragmentActivitieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ActivitiesViewModel by viewModels()
    private lateinit var adapter: ActivitieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivitieBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.menu_activities)
        setupActivitie()
    }


    private fun initRecyclerView(data: List<Activitie>) {
        adapter = ActivitieAdapter(data)
        binding.rvActivities.layoutManager = LinearLayoutManager(context)
        binding.rvActivities.adapter = adapter
    }

    private fun setupActivitie() {
        viewModel.setActivitie()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.observerActivitieList().observe(viewLifecycleOwner) {
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
                binding.progressSearch.visibility = View.VISIBLE
            } else {
                binding.progressSearch.visibility = View.GONE
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