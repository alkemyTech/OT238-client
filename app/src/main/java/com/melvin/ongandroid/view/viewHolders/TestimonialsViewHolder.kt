package com.melvin.ongandroid.view.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.databinding.FragmentActivitiesItemBinding
import com.melvin.ongandroid.databinding.FragmentTestimonialsItemBinding
import com.melvin.ongandroid.model.entities.testimonials.TestimonialsResponse
import com.squareup.picasso.Picasso
import javax.inject.Inject

class TestimonialsViewHolder
@Inject constructor(view: View): RecyclerView.ViewHolder(view) {

    val binding = FragmentTestimonialsItemBinding.bind(view)

    fun drawTestimonial(testimonial: TestimonialsResponse) {
        binding.tvActivitieTitle.text = testimonial.name
        binding.tvActivitieDescription.text = testimonial.description
        Picasso.get().load(testimonial.image).into(binding.ivActivitieImage)
    }
}

