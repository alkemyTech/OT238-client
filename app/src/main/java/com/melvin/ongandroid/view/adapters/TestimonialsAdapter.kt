package com.melvin.ongandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.entities.testimonials.TestimonialsResponse
import com.melvin.ongandroid.view.viewHolders.TestimonialsViewHolder
import javax.inject.Inject

class TestimonialsAdapter @Inject constructor(
    private val testimonials: List<TestimonialsResponse>
    ): RecyclerView.Adapter<TestimonialsViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): TestimonialsViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return TestimonialsViewHolder(
            layoutInflater.inflate(R.layout.fragment_testimonials_item, parent, false))
    }

    override fun onBindViewHolder(
        holder: TestimonialsViewHolder,
        position: Int) {

        val item = testimonials[position]
        holder.drawTestimonial(item)
    }

    override fun getItemCount(): Int {
        return testimonials.size
    }

}