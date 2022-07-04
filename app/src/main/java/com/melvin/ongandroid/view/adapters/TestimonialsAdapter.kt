package com.melvin.ongandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.entities.testimonials.Testimonials
import com.melvin.ongandroid.view.viewHolders.TestimonialsViewHolder
import javax.inject.Inject

class TestimonialsAdapter @Inject constructor(
    private val testimonials: List<Testimonials>,
    private val isMain: Boolean
    ): RecyclerView.Adapter<TestimonialsViewHolder>(){

    private val MAXITEMS = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestimonialsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (isMain) {
            TestimonialsViewHolder(layoutInflater.inflate(R.layout.testimonials_view_pager_item_home, parent, false))
        } else {
            TestimonialsViewHolder(layoutInflater.inflate(R.layout.fragment_testimonials_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: TestimonialsViewHolder, position: Int) {
        val item = testimonials[position]
        if (isMain && position <= MAXITEMS) {
           holder.drawTestimonialHome(item)
        } else if(isMain && position == MAXITEMS + 1){
            holder.drawArrow()
        } else if (!isMain) {
            holder.drawTestimonial(item)
        }
    }

    override fun getItemCount(): Int {
        return testimonials.size
    }
}