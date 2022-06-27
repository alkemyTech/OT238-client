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
    private lateinit var slideAdapter: HomeViewPagerAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initActivitiesRv()
        /*viewModel.slideList.observe(viewLifecycleOwner) {
            showActivities(homeViewModel, binding)
            loadViewPager(it.slideList)
        }*/


        return root
    }

    private fun initActivitiesRv() {
        binding.rvActivitiesSlides.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
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
        viewModel.slideList.observe(viewLifecycleOwner) {
            when (it) {
                is HomeViewModel.SlideStatus.Success -> {
                    if (it.slideList.isEmpty()) {
                        loadViewPager(it.slideList)
                    }
                }
                is HomeViewModel.SlideStatus.Failure -> {
                    Toast.makeText(context, "asd", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    /*private fun setupObersever(){
        viewModel.status.observe(viewLifecycleOwner){
            when(it){
                ApiStatus.SUCCESS ->{
                    loadViewPager(it)
                }
                ApiStatus.FAILURE -> {
                    Toast.makeText()
                }
            }
        }
    } */
}