package com.melvin.ongandroid.view.viewHolders

import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentTestimonialsItemBinding
import com.melvin.ongandroid.databinding.TestimonialsViewPagerItemHomeBinding
import com.melvin.ongandroid.model.entities.testimonials.Testimonials
import com.squareup.picasso.Picasso
import javax.inject.Inject


class TestimonialsViewHolder
@Inject constructor(var view: View): RecyclerView.ViewHolder(view) {

    private lateinit var homeBinding : TestimonialsViewPagerItemHomeBinding
    private lateinit var binding : FragmentTestimonialsItemBinding

    fun drawTestimonial(testimonial: Testimonials) {
        binding = FragmentTestimonialsItemBinding.bind(view)
        binding.tvTestimonialTitle.text = testimonial.name
        binding.tvTestimonialDescription.text = testimonial.description
        Picasso.get().load(testimonial.image).into(binding.ivTestimonialImage)
    }

    fun drawTestimonialHome(testimonial: Testimonials) {
        homeBinding = TestimonialsViewPagerItemHomeBinding.bind(view)
        homeBinding.tvTestimonialTitleHome.text = testimonial.name
        homeBinding.tvTestimonialDescriptionHome.text = testimonial.description
        Picasso.get().load(testimonial.image).into(homeBinding.ivTestimonialHome)
    }

    fun drawArrow(){
        homeBinding = TestimonialsViewPagerItemHomeBinding.bind(view)
        homeBinding.ivTestimonialHome.setImageResource(R.drawable.ic_baseline_arrow_forward_24)
        homeBinding.tvTestimonialTitleHome.setText(R.string.see_more)
        homeBinding.tvTestimonialDescriptionHome.isVisible = false
        homeBinding.cvTestimonials.isClickable = true
        homeBinding.cvTestimonials.setOnClickListener {
            it.findNavController().navigate(R.id.action_nav_home_to_testimonialsFragment)
        }
    }

}

