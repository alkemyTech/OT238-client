package com.melvin.ongandroid.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentTestimonialsBinding
import com.melvin.ongandroid.model.entities.testimonials.TestimonialsResponse
import com.melvin.ongandroid.view.adapters.TestimonialsAdapter
import com.melvin.ongandroid.viewmodel.TestimonialsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestimonialsFragment : Fragment() {

    private var _binding: FragmentTestimonialsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val testimonialsViewModel = ViewModelProvider(this)[TestimonialsViewModel::class.java]
        _binding = FragmentTestimonialsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.menu_testimonials)
    }

    private fun initRecyclerView(data: List<TestimonialsResponse>){
        binding.rvTestimonials.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvTestimonials.adapter = TestimonialsAdapter(data)
    }



}