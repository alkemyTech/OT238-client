package com.melvin.ongandroid.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.databinding.SlidesActivitiesListBinding
import com.melvin.ongandroid.view.adapters.SlideAdapter
import com.melvin.ongandroid.view.viewHolders.SlidesViewHolder
import com.melvin.ongandroid.viewmodel.HomeViewModel
import com.melvin.ongandroid.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

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
        }
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root

    }
     private fun initActivitiesRv(){
         binding.rvActivitiesSlides.layoutManager = LinearLayoutManager(activity)
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
}