package com.melvin.ongandroid.view.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.melvin.ongandroid.databinding.ViewPagerItemHomeBinding
import com.melvin.ongandroid.model.entities.slides.Slide
import com.squareup.picasso.Picasso
import javax.inject.Inject

class HomeViewHolder @Inject constructor(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ViewPagerItemHomeBinding.bind(view)

    fun drawHomeViewPager(slide: Slide) {
        Picasso.get().load(slide.imageUrl).into(binding.ivPager)
        binding.tvPagerTitle.text = slide.name
        binding.tvPagerDescription.text = slide.description
    }
}