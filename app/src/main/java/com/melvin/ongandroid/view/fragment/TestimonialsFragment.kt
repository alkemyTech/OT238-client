package com.melvin.ongandroid.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentTestimonialsBinding
import com.melvin.ongandroid.model.entities.testimonials.Testimonials
import com.melvin.ongandroid.model.entities.testimonials.TestimonialsResponse
import com.melvin.ongandroid.view.adapters.TestimonialsAdapter
import com.melvin.ongandroid.viewmodel.TestimonialsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestimonialsFragment : Fragment() {

    private val viewModel: TestimonialsViewModel by activityViewModels()
    private var _binding: FragmentTestimonialsBinding? = null
    private lateinit var adapter: TestimonialsAdapter
    private val binding get() = _binding!!

    companion object {
        fun newInstance(bundle: Bundle): TestimonialsFragment {
            val fragment = TestimonialsFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestimonialsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.menu_testimonials)
        setUpObserver()
    }

    private fun setUpObserver(){
        viewModel.testimonialsList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()){
                snackBar()
            } else {
                initRecyclerView(it)
            }
        }
    }

    private fun initRecyclerView(data: List<Testimonials>){
         adapter = TestimonialsAdapter(data, false)
         binding.rvTestimonials.layoutManager = LinearLayoutManager(context)
         binding.rvTestimonials.adapter = adapter
    }
    private fun snackBar(){
        Snackbar.make(binding.rvTestimonials, R.string.textError, Snackbar.LENGTH_LONG)
            .setAction(R.string.actionText) {
                setUpObserver()
            }
            .show()
    }
}