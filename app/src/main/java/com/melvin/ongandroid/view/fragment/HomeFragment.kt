package com.melvin.ongandroid.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.model.entities.slides.Slide
import com.melvin.ongandroid.view.adapters.HomeViewPagerAdapter
import com.melvin.ongandroid.view.adapters.SlideAdapter
import com.melvin.ongandroid.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeViewPagerAdapter

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
        viewModel.slideList.observe(viewLifecycleOwner) {
            showActivities(homeViewModel, binding)
            loadViewPager(it.slideList)
        }

        return root
    }

    private fun initActivitiesRv(){
        binding.rvActivitiesSlides.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun showActivities(viewModel: HomeViewModel, binding: FragmentHomeBinding){
        val activitiesList = viewModel.slideList.value

        if (activitiesList == null || !activitiesList.success){
            // ERROR IMPLEMENTATION
        }else{
            if(!activitiesList.slideList.isNullOrEmpty()){
                binding.rvActivitiesSlides.adapter = SlideAdapter(activitiesList.slideList)
            }else{
                // ERROR IMPLEMENTATION
            }
        }
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
}