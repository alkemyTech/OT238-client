package com.melvin.ongandroid.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.model.entities.slides.Slide
import com.melvin.ongandroid.view.adapters.HomeViewPagerAdapter
import com.melvin.ongandroid.view.adapters.SlideAdapter
import com.melvin.ongandroid.viewmodel.ApiStatus
import com.melvin.ongandroid.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeViewPagerAdapter
    private lateinit var sliderAdapter: SlideAdapter
    private val slideList = mutableListOf<Slide>()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        initActivitiesRv()
        setupSlide()

    }

    private fun initActivitiesRv() {
        binding.rvActivitiesSlides.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }
    private fun setupSlide() {
        viewModel.getSlides()
        setupObserver()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadViewPager(data: List<Slide>) {
        adapter = HomeViewPagerAdapter()
        binding.pager.adapter = adapter
        adapter.submitList(data)
    }


    private fun setupObserver() {
        viewModel.observerSlideList().observe(viewLifecycleOwner) {
            if (viewModel.observerSlideList() != null) {
                loadViewPager(it)
            } else {
                snackBar()

            }
        }
    }

    private fun snackBar() {
        Snackbar.make(binding.constraint, R.string.textError, Snackbar.LENGTH_LONG)
            .setAction(R.string.actionText) {
                setupObserver()
            }
            .show()
    }



}

